package com.aiCustomer.ai_customer_engagement_platofrm.service;

import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerInteractionRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerInteractionResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.Customer;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerInteraction;
import com.aiCustomer.ai_customer_engagement_platofrm.exception.CustomerNotFoundException;
import com.aiCustomer.ai_customer_engagement_platofrm.repository.CustomerInteractionRepository;
import com.aiCustomer.ai_customer_engagement_platofrm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerInteractionService {
    private final CustomerInteractionRepository customerInteractionRepository;
    private final CustomerRepository customerRepository;

    public CustomerInteractionService(CustomerInteractionRepository customerInteractionRepository, CustomerRepository customerRepository) {
        this.customerInteractionRepository = customerInteractionRepository;
        this.customerRepository = customerRepository;
    }
     public CustomerInteractionResponse createInteraction(Long customerId, CustomerInteractionRequest customerRequest) {
         Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: "+customerId));
         CustomerInteraction interaction = new CustomerInteraction();

         interaction.setMessage(customerRequest.getMessage());
         interaction.setChannel(customerRequest.getChannel());
         interaction.setCustomer(customer);

         CustomerInteraction savedInteraction = customerInteractionRepository.saveAndFlush(interaction);
         return new CustomerInteractionResponse(savedInteraction.getId(), savedInteraction.getMessage(), savedInteraction.getChannel(), savedInteraction.getCreatedAt(), savedInteraction.getCustomer().getId()
         );
     }

     public List<CustomerInteractionResponse> getAllInteractions() {
        return customerInteractionRepository.findAll().stream().map(interaction -> new CustomerInteractionResponse(interaction.getId(), interaction.getMessage(), interaction.getChannel(), interaction.getCreatedAt(),interaction.getCustomer().getId())).collect(Collectors.toList());
     }
     public List<CustomerInteractionResponse> getInteractions(Long customerId) {
         Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: "+customerId));
         List<CustomerInteraction> interactions = customerInteractionRepository.findByCustomer_Id(customerId);

         return interactions.stream().map(interaction -> new CustomerInteractionResponse(interaction.getId(),interaction.getMessage(),interaction.getChannel(),interaction.getCreatedAt(), interaction.getCustomer().getId()
         )).collect(Collectors.toList());
     }
}
