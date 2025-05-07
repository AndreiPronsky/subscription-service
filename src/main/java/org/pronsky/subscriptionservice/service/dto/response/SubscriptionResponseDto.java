package org.pronsky.subscriptionservice.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SubscriptionResponseDto {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
}
