package com.aiCustomer.ai_customer_engagement_platofrm.dto;

import java.time.LocalDateTime;

public class CustomerInteractionResponse {
    private Long id;
    private String message;
    private String channel;
    private LocalDateTime createdAt;
    private Long customerId;

    public CustomerInteractionResponse() {

    }
    public CustomerInteractionResponse(Long id, String message, String channel, LocalDateTime createdAt, Long customerId) {
        this.id = id;
        this.message = message;
        this.channel = channel;
        this.createdAt = createdAt;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
