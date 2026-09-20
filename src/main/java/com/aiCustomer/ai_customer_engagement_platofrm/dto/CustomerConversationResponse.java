package com.aiCustomer.ai_customer_engagement_platofrm.dto;

import java.time.LocalDateTime;

public class CustomerConversationResponse {
//    Stored conversation history
    private Long id;
    private String customerMessage;
    private String aiReply;
    private LocalDateTime createdAt;

    public CustomerConversationResponse(
            Long id,
            String customerMessage,
            String aiReply,
            LocalDateTime createdAt) {

        this.id = id;
        this.customerMessage = customerMessage;
        this.aiReply = aiReply;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public String getAiReply() {
        return aiReply;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
