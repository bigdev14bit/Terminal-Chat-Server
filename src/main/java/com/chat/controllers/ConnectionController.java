package com.chat.controllers;

import com.chat.models.dto.Packet;
import com.chat.models.enums.CommandType;
import com.chat.services.RegistrationService;
import com.chat.utils.JsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionController {
    private final int port;
    // Map storing connected userId -> Client Socket Handler thread
    private final Map<String, ClientHandler> activeConnections = new ConcurrentHashMap<>();
    private final RegistrationService registrationService = new RegistrationService();

    public ConnectionController(int port) {
        this.port = port;
    }

    public void startServer() {
        System.out.println("🚀 TCP Chat Server starting on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("🔌 New client connected: " + socket.getRemoteSocketAddress());

                ClientHandler clientHandler = new ClientHandler(socket, this);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.err.println("❌ Server error: " + e.getMessage());
        }
    }

    public Map<String, ClientHandler> getActiveConnections() {
        return activeConnections;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    // Inner class handling individual client connection threads
    public static class ClientHandler implements Runnable {
        private final Socket socket;
        private final ConnectionController server;
        private PrintWriter writer;
        private BufferedReader reader;
        private String userId;

        public ClientHandler(Socket socket, ConnectionController server) {
            this.socket = socket;
            this.server = server;
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("📩 Received raw payload: " + inputLine);

                    try {
                        Packet incomingPacket = JsonUtil.fromJson(inputLine, Packet.class);

                        if (incomingPacket.getCommand() == CommandType.REGISTER) {
                            // 1. Process registration via RegistrationService (generates tag #XXXX)
                            Packet responsePacket = server.getRegistrationService().processRegistration(incomingPacket);

                            // 2. Track connection handle
                            this.userId = responsePacket.getRecipientId();
                            server.getActiveConnections().put(this.userId, this);

                            // 3. Send registration confirmation back to Rust
                            sendPacket(responsePacket);
                        } else {
                            // Register connection userId for standard packets
                            if (incomingPacket.getSenderId() != null && this.userId == null) {
                                this.userId = incomingPacket.getSenderId();
                                server.getActiveConnections().put(this.userId, this);
                            }
                            // Echo packet
                            sendPacket(incomingPacket);
                        }
                    } catch (Exception e) {
                        System.err.println("⚠️ Could not parse packet: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("❌ Client disconnected: " + socket.getRemoteSocketAddress());
            } finally {
                if (userId != null) {
                    server.getActiveConnections().remove(userId);
                }
                try { socket.close(); } catch (Exception ignored) {}
            }
        }

        public void sendPacket(Packet packet) {
            if (writer != null) {
                writer.println(JsonUtil.toJson(packet));
            }
        }
    }
}
