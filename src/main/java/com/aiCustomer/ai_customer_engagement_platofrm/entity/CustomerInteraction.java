package com.aiCustomer.ai_customer_engagement_platofrm.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "customer_interactions")
public class CustomerInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String channel;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // many interactions for one customer

    public CustomerInteraction() {

    }

    public Long getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel){
        this.channel = channel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
