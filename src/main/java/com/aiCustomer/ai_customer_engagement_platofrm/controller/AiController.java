package com.aiCustomer.ai_customer_engagement_platofrm.controller;

import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerConversationResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerMessageRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerMessageResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import com.aiCustomer.ai_customer_engagement_platofrm.exception.InvalidPaginationException;
import com.aiCustomer.ai_customer_engagement_platofrm.service.CustomerConversationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
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
    public Page<CustomerConversationResponse> getAllConversations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        logger.info("GET /api/ai/conversations was called");
        if (page < 0) {
            throw new InvalidPaginationException("Page Cannot be negative");
        }
        if (size < 1 || size > 50) {
            throw new InvalidPaginationException("Size must be between 1 and 50");
        }
        Pageable pageable = PageRequest.of(page, size);

        Page<CustomerConversationResponse> conversations = conversationService.getAllConversations(pageable);
        logger.info("Found {} conversations",conversations.getTotalElements());
        return conversations;
    }

    @GetMapping("/conversations/{id}")
    public CustomerConversationResponse getConversationById(@PathVariable Long id){
        logger.info("GET /api/ai/conversations was called {}",id);
        CustomerConversationResponse conversation = conversationService.getConversationById(id);
        logger.info("Conversation found with ID: {}",conversation.getId());
        return conversation;
    }

    @GetMapping("/conversations/search")
    public Page<CustomerConversationResponse> searchConversations(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt,desc") String sort ) {
        logger.info("searching conversations with keyword {}",keyword);
        if (page < 0) {
            throw new InvalidPaginationException("Page Cannot be negative");
        }

        if (size < 1 || size > 50) {
            throw new InvalidPaginationException("Size must be between 1 and 50");
        }

        String[] sortDetails = sort.split(",");
        String field = sortDetails[0];
        String direction = sortDetails.length > 1 ? sortDetails[1] : "asc";

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, field));
        return conversationService.searchConversions(keyword, pageable);
    }

    @GetMapping("/conversations/filter")
    public Page<CustomerConversationResponse> filterByDate(
            @RequestParam LocalDateTime fromDate,
            @RequestParam LocalDateTime toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        if (page < 0) {
            throw new InvalidPaginationException("Page Cannot be negative");
        }

        if (size < 1 || size > 50) {
            throw new InvalidPaginationException("Size must be between 1 and 50");
        }

        String[] sortDetails = sort.split(",");
        String field = sortDetails[0];
        String direction = sortDetails.length > 1 ? sortDetails[1] : "asc";

        Sort.Direction sortDirection =
                Sort.Direction.fromString(direction);

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortDirection, field)
                );

        return conversationService.getConversationsByDateRange(
                fromDate,
                toDate,
                pageable
        );
    }
}