package org.pronsky.subscriptionservice.service.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.pronsky.subscriptionservice.data.entity.Subscription;

import java.util.List;

@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String username;
    private List<Subscription> subscriptions;
}
