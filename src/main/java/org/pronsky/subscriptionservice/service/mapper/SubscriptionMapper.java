package org.pronsky.subscriptionservice.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.subscriptionservice.data.entity.Subscription;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponseDto subscriptionToSubscriptionResponseDto(Subscription subscription);

}
