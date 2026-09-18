package com.aiCustomer.ai_customer_engagement_platofrm.service;

import com.aiCustomer.ai_customer_engagement_platofrm.dto.OllamaMessage;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.OllamaRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class OllamaService {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    public OllamaService() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:11434").build();
        this.objectMapper = new ObjectMapper();
    }

    public String askOllama(String message) {
        //create request for ollama
        OllamaRequest request = new OllamaRequest("qwen3:0.6b", List.of(new OllamaMessage("user", message)), false);

        //send request to ollama
        String response = restClient.post().uri("/api/chat").body(request).retrieve().body(String.class);

        //read ollama json response
        try{
              JsonNode json = objectMapper.readTree(response);
          //get message
              JsonNode messageNode = json.get("message");

              return messageNode.get("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read ollama response ",e);
        }
    }
}
