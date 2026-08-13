package com.aiCustomer.ai_customer_engagement_platofrm.dto;
import jakarta.validation.constraints.NotBlank;

public class CustomerInteractionRequest {
    @NotBlank(message = "Message is Required")
    private String message;

    @NotBlank(message = "Channel is Required")
    private String channel;

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
