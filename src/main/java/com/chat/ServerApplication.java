package com.chat;

import com.chat.controllers.ConnectionController;

public class ServerApplication {
    public static void main(String[] args) {
        int PORT = 8080;
        ConnectionController connectionController = new ConnectionController(PORT);
        connectionController.startServer();
    }
}
