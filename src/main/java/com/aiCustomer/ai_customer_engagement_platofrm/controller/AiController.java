package com.aiCustomer.ai_customer_engagement_platofrm.controller;

import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerMessageRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerMessageResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import com.aiCustomer.ai_customer_engagement_platofrm.service.CustomerConversationService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    private static final Logger logger = LoggerFactory.getLogger(AiController.class);
    private final CustomerConversationService  conversationService;

    public AiController(CustomerConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/test")
    public String test() {
        logger.info("GET /api/ai/test was called");
        return "Ai Service is running";
    }
    @PostMapping("/chat")
    public  CustomerMessageResponse chat(@RequestBody CustomerMessageRequest request) {
        logger.info("POST /api/ai/chat was called");
        logger.info("Customer message: {}", request.getMessage());

        CustomerConversation conversation = conversationService.processMessage(request.getMessage());
        logger.info("Conversation saved with ID: {}",conversation.getId());
        logger.info("Sending AI response back to customer");

        return new CustomerMessageResponse(conversation.getAiReply(), "qwen3:0.6b");
    }

    @GetMapping("/conversations")
    public List<CustomerConversation> getConversations() {
        logger.info("GET /api/ai/conversations was called");

        List<CustomerConversation> conversations = conversationService.getAllConversations();
        logger.info("Found {} conversations",conversations.size());
        return conversations;
    }

    @GetMapping("/conversations/{id}")
    public CustomerConversation getConversationById(@PathVariable Long id){
        logger.info("GET /api/ai/conversations was called {}",id);
        CustomerConversation conversation = conversationService.getConversationById(id);
        logger.info("Conversation found with ID: {}",conversation.getId());
        return conversation;
    }
}