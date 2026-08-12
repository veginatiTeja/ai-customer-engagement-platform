package com.aiCustomer.ai_customer_engagement_platofrm.controller;

import com.aiCustomer.ai_customer_engagement_platofrm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.Customer;

import java.util.List;

@RestController //it tells spring  this class handles http requests and responses
@RequestMapping("/api/customer") //this creates common url prefix
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping //this method handles http requests
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
       CustomerResponse response = customerService.createCustomer(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } //@Request body takes json from http request body
    //CustomerRequest convert JSON INTO this java type object
    //request variable/reference holding that object

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
         customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
