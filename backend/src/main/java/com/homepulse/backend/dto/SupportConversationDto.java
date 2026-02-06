package com.homepulse.backend.dto;

import java.time.Instant;

public class SupportConversationDto {
    private Long id;
    private Long userId;
    private String subject;
    private String status;
    private Instant createdAt;

    public SupportConversationDto() {
    }

    public SupportConversationDto(Long id, Long userId, String subject, String status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
