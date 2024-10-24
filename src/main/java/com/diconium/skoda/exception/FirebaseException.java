package com.diconium.skoda.exception;

import org.springframework.http.HttpStatus;

public class FirebaseException extends RuntimeException {
    private static final HttpStatus STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR;

    public FirebaseException(final String vin, final String title, final String message) {
        super("Error while sending notification to vin: " + vin + ", title: " + title + ", message: " + message);
    }

    public static HttpStatus getStatusCode() {
        return STATUS_CODE;
    }
}
