package com.diconium.skoda.exception;

import org.springframework.http.HttpStatus;

public class VinNotFoundException extends RuntimeException {
    private static final HttpStatus STATUS_CODE = HttpStatus.NOT_FOUND;

    public VinNotFoundException(final String vin) {
        super("No car found for VIN: " + vin);
    }

    public static HttpStatus getStatusCode() {
        return STATUS_CODE;
    }
}
