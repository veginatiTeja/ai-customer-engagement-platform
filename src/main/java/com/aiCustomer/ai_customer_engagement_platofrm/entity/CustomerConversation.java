package com.aiCustomer.ai_customer_engagement_platofrm.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CustomerConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String customerMessage;

    @Column(columnDefinition = "TEXT")
    private String aiReply;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    public Long getId() {
        return  id;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }
    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public String getAiReply() {
        return aiReply;
    }

    public void setAiReply(String aiReply) {
        this.aiReply = aiReply;
    }

    public LocalDateTime getCreatedAt() {
        return  createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
