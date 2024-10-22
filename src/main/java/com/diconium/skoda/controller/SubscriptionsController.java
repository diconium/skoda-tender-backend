package com.diconium.skoda.controller;

import com.diconium.skoda.model.dto.SubscriptionsDto;
import com.diconium.skoda.service.SubscriptionsService;
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

    @GetMapping("/{vin}/status")
    public SubscriptionsDto status(final @PathVariable String vin) {
        LOGGER.debug("Getting subscriptions for VIN: {}", vin);

        return subscriptionsService.getAllServicesForVin(vin);
    }
}
