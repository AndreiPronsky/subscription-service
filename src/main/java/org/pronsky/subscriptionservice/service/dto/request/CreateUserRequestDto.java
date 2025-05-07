package org.pronsky.subscriptionservice.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
