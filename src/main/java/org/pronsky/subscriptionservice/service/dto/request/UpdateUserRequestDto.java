package org.pronsky.subscriptionservice.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserRequestDto {

    @NotNull
    private Long id;
    private String username;
    private String password;
}
