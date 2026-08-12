package com.aiCustomer.ai_customer_engagement_platofrm.controller;
import com.aiCustomer.ai_customer_engagement_platofrm.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this is also hellocontroller beans
public class HelloController {
    private final CustomerService customerService;
    public HelloController(CustomerService customerService) {
        this.customerService = customerService;
    } // This is dependency injection
    //Spring container creates CustomerService bean ,this injects in Hello controller this is called dependency injection

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to AI Customer Engagement Platform";
    }

    @GetMapping("/customer")
    public String getCustomer() {
        return  customerService.getCustomer();
    }
}