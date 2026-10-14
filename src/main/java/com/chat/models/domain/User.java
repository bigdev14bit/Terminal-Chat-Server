package com.chat.models.domain;

import com.chat.models.enums.UserStatus;
import java.time.Instant;
import java.util.Random;

public class User {
    private String id;
    private String fullName;
    private String username;
    private String tagSlug;     // e.g., #3849
    private String passwordHash;
    private String secretWord;   // Used for recovery/verification
    private UserStatus status;
    private Instant createdAt;

    public User() {}

    public User(String id, String fullName, String username, String passwordHash, String secretWord) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.tagSlug = generateSlug();
        this.passwordHash = passwordHash;
        this.secretWord = secretWord;
        this.status = UserStatus.OFFLINE;
        this.createdAt = Instant.now();
    }

    private String generateSlug() {
        Random random = new Random();
        int number = random.nextInt(9000) + 1000; // 4-digit number (1000-9999)
        return "#" + number;
    }

    public String getFullHandle() {
        return "@" + username + tagSlug; // e.g., @solomon#3849
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTagSlug() { return tagSlug; }
    public void setTagSlug(String tagSlug) { this.tagSlug = tagSlug; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSecretWord() { return secretWord; }
    public void setSecretWord(String secretWord) { this.secretWord = secretWord; }

    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
