package com.aiCustomer.ai_customer_engagement_platofrm.repository;

import com.aiCustomer.ai_customer_engagement_platofrm.entity.CustomerConversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerConversationRepository extends JpaRepository<CustomerConversation, Long> {
    Page<CustomerConversation> findByCustomerMessageContainingIgnoreCase(String message, Pageable pageable);

    @Query("""
     SELECT c FROM CustomerConversation c WHERE LOWER(c.customerMessage) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.aiReply) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    Page<CustomerConversation> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);


    @Query("""
      SELECT c FROM CustomerConversation c WHERE c.createdAt BETWEEN :fromDate AND :toDate
""")
    Page<CustomerConversation> findByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, Pageable pageable);
}
