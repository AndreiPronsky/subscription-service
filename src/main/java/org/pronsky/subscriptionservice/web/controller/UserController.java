package org.pronsky.subscriptionservice.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.subscriptionservice.service.UserService;
import org.pronsky.subscriptionservice.service.dto.request.AddSubscriptionRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.CreateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.UpdateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.pronsky.subscriptionservice.service.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequestDto request) {
        UserResponseDto createdUser = userService.createUser(request);

        URI location = URI.create("/users/" + createdUser.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserRequestDto userDto) {
        return ResponseEntity.accepted().body(userService.updateUser(userDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<Void> subscribe(@RequestBody AddSubscriptionRequestDto requestDto) {
        userService.addSubscriptionToTheUser(requestDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{id}/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscriptionsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getSubscriptionsByUserId(id));
    }

    @DeleteMapping("{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        userService.unsubscribeUser(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
