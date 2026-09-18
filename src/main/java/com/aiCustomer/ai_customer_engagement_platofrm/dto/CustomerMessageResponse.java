package com.aiCustomer.ai_customer_engagement_platofrm.dto;

public class CustomerMessageResponse {
    private String reply;
    private String model;

    public CustomerMessageResponse(String reply, String model) {
        this.reply = reply;
        this.model = model;
    }
    public String getReply() {
        return reply;
    }
    public String getModel() {
        return model;
    }
}
