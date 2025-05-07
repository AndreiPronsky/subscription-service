package org.pronsky.subscriptionservice.service;

import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionResponseDto> getTopThreeSubscriptions();
}
