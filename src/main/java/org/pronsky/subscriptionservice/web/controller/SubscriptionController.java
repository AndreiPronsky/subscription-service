package org.pronsky.subscriptionservice.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get top 3 most popular subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top 3 subscriptions retrieved",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No subscriptions found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionResponseDto>> getTopThree() {
        return ResponseEntity.ok(subscriptionService.getTopThreeSubscriptions());
    }
}
