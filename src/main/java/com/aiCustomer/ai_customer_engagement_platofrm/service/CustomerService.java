package com.aiCustomer.ai_customer_engagement_platofrm.service;
import org.springframework.stereotype.Service;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerRequest;
import com.aiCustomer.ai_customer_engagement_platofrm.dto.CustomerResponse;
import com.aiCustomer.ai_customer_engagement_platofrm.repository.CustomerRepository;
import com.aiCustomer.ai_customer_engagement_platofrm.exception.CustomerNotFoundException;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.Customer;
import java.util.List;

@Service // spring container creates CustomerService bean
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public String getCustomer() {
        return "Customer data service";
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());

        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }

    public CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(this::convertToResponse).toList();
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: "+id));
        return convertToResponse(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id : "+id));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToResponse(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

}