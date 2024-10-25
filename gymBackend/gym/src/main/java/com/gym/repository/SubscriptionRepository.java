package com.gym.repository;

import com.gym.model.Subscription;
import com.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCustomer(User customer);
    List<Subscription> findByActive(boolean active);
    Optional<Subscription> findFirstByCustomerIdAndActiveTrue(Long customerId);}

