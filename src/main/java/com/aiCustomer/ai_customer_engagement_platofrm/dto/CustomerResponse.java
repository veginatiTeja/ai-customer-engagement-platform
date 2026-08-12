package com.aiCustomer.ai_customer_engagement_platofrm.dto;

public class CustomerResponse {

    private Long id;
    private String message;
    private String name;
    private String email;

    public CustomerResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}