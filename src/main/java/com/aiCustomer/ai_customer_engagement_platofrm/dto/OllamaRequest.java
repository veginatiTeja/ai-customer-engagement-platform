package com.aiCustomer.ai_customer_engagement_platofrm.dto;

import java.util.List;

public class OllamaRequest {
    private String model;
    private boolean stream;
    private List<OllamaMessage> messages;

    public OllamaRequest() {

    }
    public OllamaRequest(String model, List<OllamaMessage> messages, boolean stream) {
       this.model = model;
       this.messages = messages;
       this.stream = stream;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public List<OllamaMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<OllamaMessage> messages) {
        this.messages = messages;
    }
    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

}
