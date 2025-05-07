package org.pronsky.subscriptionservice.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequestDto {

    private String username;
    private String password;
}
