package org.pronsky.subscriptionservice.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddSubscriptionRequestDto {

    private Long userId;
    private Long subscriptionId;
}
