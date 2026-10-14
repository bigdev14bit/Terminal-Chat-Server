package com.chat.models.domain;

import com.chat.models.enums.MessageType;
import java.time.Instant;

public class Message {
    private String id;
    private String senderId;
    private String recipientId; // Can be a handle (@alex) or channel name (#dev-rust)
    private String content;
    private MessageType type;
    private long timestamp;

    public Message() {
        this.timestamp = Instant.now().getEpochSecond();
    }

    public Message(String id, String senderId, String recipientId, String content, MessageType type) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.type = type;
        this.timestamp = Instant.now().getEpochSecond();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
