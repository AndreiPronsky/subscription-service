package org.pronsky.subscriptionservice.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.subscriptionservice.service.SubscriptionService;
import org.pronsky.subscriptionservice.service.dto.response.SubscriptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionResponseDto>> getTopThree() {
        return ResponseEntity.ok(subscriptionService.getTopThreeSubscriptions());
    }
}
