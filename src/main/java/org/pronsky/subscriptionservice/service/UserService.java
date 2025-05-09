package org.pronsky.subscriptionservice.service;

import org.pronsky.subscriptionservice.service.dto.request.CreateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.UpdateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.pronsky.subscriptionservice.service.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto registerUser(CreateUserRequestDto userDto);

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(UpdateUserRequestDto userDto);

    void deleteUser(Long id);

    void subscribeUser(Long userId, Long subscriptionId);

    List<SubscriptionResponseDto> getSubscriptionsByUserId(Long id);

    void unsubscribeUser(Long userId, Long subscriptionId);
}
