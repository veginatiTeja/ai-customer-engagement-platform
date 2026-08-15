package com.aiCustomer.ai_customer_engagement_platofrm.controller;

import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerInteractionRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerInteractionResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerInteraction;
import com.aiCustomer.ai_customer_engagement_platofrm.service.CustomerInteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class CustomerInteractionController {
    private final CustomerInteractionService customerInteractionService;

    public CustomerInteractionController(CustomerInteractionService customerInteractionService) {
        this.customerInteractionService = customerInteractionService;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CustomerInteractionResponse> createInteraction(@PathVariable Long customerId, @RequestBody CustomerInteractionRequest request) {
         CustomerInteractionResponse response = customerInteractionService.createInteraction(customerId,request);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<CustomerInteractionResponse> getAllInteractions() {
        return customerInteractionService.getAllInteractions();
    }

    @GetMapping("/customer/{customerId}")
    public List<CustomerInteractionResponse> getInteractionByCustomer(@PathVariable Long customerId) {
         return customerInteractionService.getInteractions(customerId);
    }
}
