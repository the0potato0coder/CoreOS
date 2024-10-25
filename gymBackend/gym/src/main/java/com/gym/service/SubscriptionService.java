package com.gym.service;

import com.gym.exception.SubscriptionException;
import com.gym.model.*;
import com.gym.repository.SubscriptionRepository;
import com.gym.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Subscription createSubscription(Long customerId, SubscriptionType type) {
        // Find the customer
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new SubscriptionException("Customer not found"));

        // Check if customer already has an active subscription
        subscriptionRepository.findFirstByCustomerIdAndActiveTrue(customerId)
                .ifPresent(s -> {
                    throw new SubscriptionException("Customer already has an active subscription");
                });

        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setType(type);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(calculateEndDate(type));
        subscription.setActive(true);
        subscription.setAmount(calculateAmount(type));

        return subscriptionRepository.save(subscription);
    }

    private LocalDateTime calculateEndDate(SubscriptionType type) {
        if (type == null) {
            throw new SubscriptionException("Subscription type cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();
        return switch (type) {
            case DAYS_30 -> now.plusDays(30);
            case DAYS_90 -> now.plusDays(90);
            case DAYS_180 -> now.plusDays(180);
            case DAYS_360 -> now.plusDays(360);
        };
    }

    private double calculateAmount(SubscriptionType type) {
        if (type == null) {
            throw new SubscriptionException("Subscription type cannot be null");
        }

        return switch (type) {
            case DAYS_30 -> 50.0;
            case DAYS_90 -> 130.0;
            case DAYS_180 -> 240.0;
            case DAYS_360 -> 450.0;
        };
    }

    public Subscription getActiveSubscription(Long customerId) {
        if (customerId == null) {
            throw new SubscriptionException("Customer ID cannot be null");
        }

        return subscriptionRepository.findFirstByCustomerIdAndActiveTrue(customerId)
                .orElseThrow(() -> new SubscriptionException("No active subscription found"));
    }
}
