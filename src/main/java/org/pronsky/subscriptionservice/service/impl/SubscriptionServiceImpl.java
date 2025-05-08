package org.pronsky.subscriptionservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.subscriptionservice.data.entity.Subscription;
import org.pronsky.subscriptionservice.data.repository.SubscriptionRepository;
import org.pronsky.subscriptionservice.service.SubscriptionService;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.pronsky.subscriptionservice.service.mapper.SubscriptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionResponseDto> getTopThreeSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findTop3MostPopularSubscriptions();
        if (subscriptions.isEmpty()) {
            throw new EntityNotFoundException("Subscriptions not found");
        }
        return subscriptions.stream()
                .map(subscriptionMapper::subscriptionToSubscriptionResponseDto)
                .toList();
    }
}
