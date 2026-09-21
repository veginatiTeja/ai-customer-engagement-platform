package com.aiCustomer.ai_customer_engagement_platofrm.repository;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerConversationRepository extends JpaRepository<CustomerConversation, Long> {
    Page<CustomerConversation> findByCustomerMessageContainingIgnoreCase(String message, Pageable pageable);
}
