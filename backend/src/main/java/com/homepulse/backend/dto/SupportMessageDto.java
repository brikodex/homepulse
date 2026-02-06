package com.homepulse.backend.dto;

import java.time.Instant;

public class SupportMessageDto {
    private Long id;
    private Long conversationId;
    private String senderRole;
    private String message;
    private Instant createdAt;

    public SupportMessageDto() {
    }

    public SupportMessageDto(Long id, Long conversationId, String senderRole, String message, Instant createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderRole = senderRole;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
