package org.pronsky.subscriptionservice.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserRequestDto {

    private Long id;
    private String username;
    private String password;
}
