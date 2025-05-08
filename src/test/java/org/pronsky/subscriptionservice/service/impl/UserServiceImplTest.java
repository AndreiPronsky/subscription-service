package org.pronsky.subscriptionservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pronsky.subscriptionservice.data.entity.Subscription;
import org.pronsky.subscriptionservice.data.entity.User;
import org.pronsky.subscriptionservice.data.repository.SubscriptionRepository;
import org.pronsky.subscriptionservice.data.repository.UserRepository;
import org.pronsky.subscriptionservice.service.dto.request.CreateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.UpdateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.pronsky.subscriptionservice.service.dto.response.UserResponseDto;
import org.pronsky.subscriptionservice.service.mapper.SubscriptionMapper;
import org.pronsky.subscriptionservice.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_shouldSaveAndReturnUserResponseDto() {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setPassword("raw");

        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(passwordEncoder.encode("raw")).thenReturn("encoded");
        when(userMapper.createUserRequestDtoToUser(requestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.registerUser(requestDto);

        assertEquals(responseDto, result);
        verify(userRepository).save(user);
    }

    @Test
    void getUserById_shouldReturnUserResponseDto() {
        Long id = 1L;
        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.userToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.getUserById(id);

        assertEquals(responseDto, result);
    }

    @Test
    void getUserById_shouldThrowException_whenUserNotFound() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.getUserById(id));

        assertTrue(exception.getMessage().contains("User with id"));
    }

    @Test
    void updateUser_shouldEncodePasswordAndReturnDto() {
        UpdateUserRequestDto updateDto = new UpdateUserRequestDto();
        updateDto.setPassword("plain");

        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        when(userMapper.updateUserRequestDtoToUser(updateDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.updateUser(updateDto);

        assertEquals(responseDto, result);
    }

    @Test
    void deleteUser_shouldCallRepositoryDeactivate() {
        Long id = 5L;

        userService.deleteUser(id);

        verify(userRepository).deactivateById(id);
    }

    @Test
    void subscribeUser_shouldAddSubscription() {
        Long userId = 1L;
        Long subId = 2L;

        User user = new User();
        user.setSubscriptions(new HashSet<>());

        Subscription subscription = new Subscription();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(subscriptionRepository.findById(subId)).thenReturn(Optional.of(subscription));

        userService.subscribeUser(userId, subId);

        assertTrue(user.getSubscriptions().contains(subscription));
    }

    @Test
    void subscribeUser_shouldThrowException_whenUserNotFound() {
        Long userId = 1L, subId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.subscribeUser(userId, subId));
    }

    @Test
    void getSubscriptionsByUserId_shouldMapAndReturnDtoList() {
        Long id = 1L;

        Subscription sub = new Subscription();
        SubscriptionResponseDto dto = new SubscriptionResponseDto();

        User user = new User();
        user.setSubscriptions(Set.of(sub));

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(subscriptionMapper.subscriptionToSubscriptionResponseDto(sub)).thenReturn(dto);

        List<SubscriptionResponseDto> result = userService.getSubscriptionsByUserId(id);

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void unsubscribeUser_shouldRemoveSubscription() {
        Long userId = 1L, subId = 2L;

        Subscription subscription = new Subscription();
        User user = new User();
        user.setSubscriptions(new HashSet<>(Set.of(subscription)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(subscriptionRepository.findById(subId)).thenReturn(Optional.of(subscription));

        userService.unsubscribeUser(userId, subId);

        assertFalse(user.getSubscriptions().contains(subscription));
    }
}

