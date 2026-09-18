package com.aiCustomer.ai_customer_engagement_platofrm.service;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import com.aiCustomer.ai_customer_engagement_platofrm.repository.CustomerConversationRepository;
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

    public List<CustomerConversation> getAllConversations() {
        return repository.findAll();
    }

    public CustomerConversation getConversationById(Long id) {
        logger.info("fetching get conversation by id {}", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Conversation not found with ID: "+id));
    }


}
