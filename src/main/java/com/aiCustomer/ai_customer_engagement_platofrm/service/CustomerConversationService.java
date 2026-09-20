package com.aiCustomer.ai_customer_engagement_platofrm.service;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import com.aiCustomer.ai_customer_engagement_platofrm.exception.ConversationNotFoundException;
import com.aiCustomer.ai_customer_engagement_platofrm.repository.CustomerConversationRepository;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerConversationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConversationService {
    private final static Logger logger = LoggerFactory.getLogger(CustomerConversationService.class);
    private final CustomerConversationRepository repository;
    private final OllamaService ollamaService;

    public CustomerConversationService(CustomerConversationRepository repository, OllamaService ollamaService) {
        this.repository = repository;
        this.ollamaService = ollamaService;
    }

    public CustomerConversation processMessage(String customerMessage) {
        logger.info("processing customer message");
        logger.info("Calling ollama");
       //Ask ollama
        String aiReply = ollamaService.askOllama(customerMessage);
        logger.info("Received response from Ollama");

        // create conversation object
        CustomerConversation conversation = new CustomerConversation();

        conversation.setAiReply(aiReply);
        conversation.setCustomerMessage(customerMessage);
        logger.info("Saving conversation to PostgreSQL");

        CustomerConversation savedConversation  = repository.save(conversation);
        logger.info("Conversation saved successfully");

        //save conversation in postgresql
        return savedConversation;
    }

    public Page<CustomerConversationResponse> getAllConversations(Pageable pageable) {
        Page<CustomerConversation> conversations = repository.findAll(pageable);
        return conversations.map(conversion -> new CustomerConversationResponse(conversion.getId(),conversion.getCustomerMessage(),conversion.getAiReply(), conversion.getCreatedAt()));
    }

    public CustomerConversationResponse getConversationById(Long id) {
        logger.info("fetching get conversation by id {}", id);
        CustomerConversation conversation= repository.findById(id).orElseThrow(() -> new ConversationNotFoundException("Conversation not found with ID: "+id));
        return new CustomerConversationResponse(conversation.getId(), conversation.getCustomerMessage(), conversation.getAiReply(), conversation.getCreatedAt());
    }


}
