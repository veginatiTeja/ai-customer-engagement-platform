package com.aiCustomer.ai_customer_engagement_platofrm.repository;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerInteractionRepository extends JpaRepository<CustomerInteraction, Long>  {
    List<CustomerInteraction> findByCustomer_Id(Long CustomerId);  //This is Derived Query Method , Spring generates the repository implementation and query for you.
}
