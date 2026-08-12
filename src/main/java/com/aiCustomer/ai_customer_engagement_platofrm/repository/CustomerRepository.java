package com.aiCustomer.ai_customer_engagement_platofrm.repository;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//give me a repository that performs db operations for the customer entity , whose ID is Long
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
