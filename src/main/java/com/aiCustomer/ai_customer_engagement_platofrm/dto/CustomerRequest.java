package com.aiCustomer.ai_customer_engagement_platofrm.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
    @NotBlank(message="Name is Required")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Email must be valid")
    private String email;

    public CustomerRequest() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}