package com.chat;

import com.chat.models.dto.Packet;
import com.chat.models.enums.CommandType;
import com.chat.services.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RegistrationService registrationService = new RegistrationService();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outWriter = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String rawJsonLine;
            while ((rawJsonLine = inReader.readLine()) != null) {
                // Parse incoming JSON packet from Rust client
                Packet incomingPacket = objectMapper.readValue(rawJsonLine, Packet.class);

                // Handle registration
                if (incomingPacket.getCommand() == CommandType.REGISTER) {
                    Packet responsePacket = registrationService.processRegistration(incomingPacket);

                    // Serialize response and send back over socket stream
                    String jsonResponse = objectMapper.writeValueAsString(responsePacket);
                    outWriter.println(jsonResponse);
                }
            }
        } catch (Exception e) {
            System.err.println("Client handler error: " + e.getMessage());
        }
    }
}
