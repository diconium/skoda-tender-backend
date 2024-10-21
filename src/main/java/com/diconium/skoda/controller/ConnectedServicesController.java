package com.diconium.skoda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connected-services")
public class ConnectedServicesController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConnectedServicesController.class);

    @GetMapping("/hello-world")
    public String helloWorld() {
        LOGGER.info("Hello World");

        return "Hello World";
    }

    @GetMapping("/status/{vin}")
    public String status(final String vin) {
        LOGGER.info("Status for VIN: {}", vin);

        return "Status for VIN: " + vin;
    }
}
