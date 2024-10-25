package com.gym.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    private Long customerId;
    private SubscriptionType subscriptionType;
}