package com.aiCustomer.ai_customer_engagement_platofrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aiCustomer.ai_customer_engagement_platofrm.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
