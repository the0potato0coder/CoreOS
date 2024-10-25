package com.gym.controller;

import com.gym.exception.SubscriptionException;
import com.gym.model.Subscription;
import com.gym.model.SubscriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gym.service.SubscriptionService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscriptions")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionRequest request) {
        try {
            if (request == null || request.getCustomerId() == null || request.getSubscriptionType() == null) {
                return ResponseEntity.badRequest().body("Invalid request parameters");
            }

            Subscription subscription = subscriptionService.createSubscription(
                    request.getCustomerId(),
                    request.getSubscriptionType()
            );
            return ResponseEntity.ok(subscription);
        } catch (SubscriptionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/subscriptions/active")
    public ResponseEntity<?> getActiveSubscription(@RequestParam Long customerId) {
        try {
            Subscription subscription = subscriptionService.getActiveSubscription(customerId);
            return ResponseEntity.ok(subscription);
        } catch (SubscriptionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}