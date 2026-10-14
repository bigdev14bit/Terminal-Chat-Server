package com.chat.models.dto;

import com.chat.models.enums.CommandType;
import com.chat.models.enums.MessageType;

public class Packet {
    private String packetId;
    private CommandType command;
    private MessageType messageType;
    private String senderId;
    private String recipientId; // User ID for DM, or Group ID for GC
    private String payload;     // Text message, code text, Base64 chunk, or Math sticker JSON
    private String language;    // Programming language if messageType == CODE
    private long timestamp;

    public Packet() {
        this.timestamp = System.currentTimeMillis();
    }

    public Packet(String packetId, CommandType command, MessageType messageType,
                  String senderId, String recipientId, String payload, String language) {
        this.packetId = packetId;
        this.command = command;
        this.messageType = messageType;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.payload = payload;
        this.language = language;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getPacketId() { return packetId; }
    public void setPacketId(String packetId) { this.packetId = packetId; }

    public CommandType getCommand() { return command; }
    public void setCommand(CommandType command) { this.command = command; }

    public MessageType getMessageType() { return messageType; }
    public void setMessageType(MessageType messageType) { this.messageType = messageType; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
