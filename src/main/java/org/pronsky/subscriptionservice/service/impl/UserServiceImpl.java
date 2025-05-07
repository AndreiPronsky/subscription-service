package org.pronsky.subscriptionservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.subscriptionservice.data.entity.User;
import org.pronsky.subscriptionservice.data.repository.SubscriptionRepository;
import org.pronsky.subscriptionservice.data.repository.UserRepository;
import org.pronsky.subscriptionservice.service.UserService;
import org.pronsky.subscriptionservice.service.dto.request.CreateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.UpdateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.pronsky.subscriptionservice.service.dto.response.UserResponseDto;
import org.pronsky.subscriptionservice.service.mapper.SubscriptionMapper;
import org.pronsky.subscriptionservice.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String SUBSCRIPTION_NOT_FOUND = "Subscription with id %s not found";
    public static final String USER_NOT_FOUND = "User with id %s not found";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public UserResponseDto registerUser(CreateUserRequestDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(userMapper.createUserRequestDtoToUser(userDto));

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userMapper.userToUserResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND, id))));
    }

    @Override
    public UserResponseDto updateUser(UpdateUserRequestDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(userMapper.updateUserRequestDtoToUser(userDto));

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deactivateById(id);
    }

    @Override
    public void subscribeUser(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND, userId)));
        user.getSubscriptions().add(subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SUBSCRIPTION_NOT_FOUND, subscriptionId))));

    }

    @Override
    public List<SubscriptionResponseDto> getSubscriptionsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND, id)));
        return user.getSubscriptions().stream()
                .map(subscriptionMapper::subscriptionToSubscriptionResponseDto)
                .toList();
    }

    @Override
    public void unsubscribeUser(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND, userId)));
        user.getSubscriptions().remove(subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SUBSCRIPTION_NOT_FOUND, subscriptionId))));
    }
}
