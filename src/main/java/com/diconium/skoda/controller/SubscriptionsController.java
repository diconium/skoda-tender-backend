package com.diconium.skoda.controller;

import com.diconium.skoda.model.dto.ErrorResponseDto;
import com.diconium.skoda.model.dto.SubscriptionsDto;
import com.diconium.skoda.service.SubscriptionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionsController.class);

    private final SubscriptionsService subscriptionsService;

    public SubscriptionsController(final SubscriptionsService subscriptionsService) {
        this.subscriptionsService = subscriptionsService;
    }

    @Operation(
            summary = "Retrieve all subscription services for a given VIN",
            description =
                    "Fetches all subscription services associated with the specified Vehicle Identification Number (VIN). "
                            + "Returns detailed information about the user, car, and the list of subscription services.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved services"),
                @ApiResponse(
                        responseCode = "404",
                        description = "VIN not found",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = ErrorResponseDto.class)))
            })
    @GetMapping("/{vin}/status")
    public SubscriptionsDto status(final @PathVariable String vin) {
        LOGGER.debug("Getting subscriptions for VIN: {}", vin);

        return subscriptionsService.getAllServicesForVin(vin);
    }
}
