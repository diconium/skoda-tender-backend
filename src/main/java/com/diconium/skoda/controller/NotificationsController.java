package com.diconium.skoda.controller;

import com.diconium.skoda.model.dto.ErrorResponseDto;
import com.diconium.skoda.model.dto.NotificationDto;
import com.diconium.skoda.service.NotificationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(NotificationsController.class);

    private final NotificationsService notificationsService;

    public NotificationsController(final NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @Operation(
            summary = "Send a notification",
            description =
                    "Sends a notification using the provided NotificationDto. The notification includes details such as VIN, title, and message.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Notification sent successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal server error",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = ErrorResponseDto.class)))
            })
    @PostMapping("/send")
    public ResponseEntity<Void> send(@Valid @RequestBody final NotificationDto notificationDto) {
        LOGGER.debug("Sending notification: {}", notificationDto);

        notificationsService.sendNotification(notificationDto);

        return ResponseEntity.ok().build();
    }
}
