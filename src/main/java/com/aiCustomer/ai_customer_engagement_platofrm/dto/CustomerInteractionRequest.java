package com.aiCustomer.ai_customer_engagement_platofrm.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerInteractionRequest {

    @NotNull
    private Long customerId;

    @NotBlank(message = "Message is Required")
    private String message;

    @NotBlank(message = "Channel is Required")
    private String channel;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
