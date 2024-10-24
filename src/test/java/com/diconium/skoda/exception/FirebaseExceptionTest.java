package com.diconium.skoda.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class FirebaseExceptionTest {

    @Test
    void constructor_shouldSetCorrectMessage() {
        var vin = "1HGCM82633A123456";
        var title = "Test Title";
        var message = "Test Message";
        var exception = new FirebaseException(vin, title, message);

        assertEquals(
                "Error while sending notification to vin: " + vin + ", title: " + title + ", message: " + message,
                exception.getMessage());
    }

    @Test
    void getStatusCode_shouldReturnInternalServerErrorStatus() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, FirebaseException.getStatusCode());
    }

    @Test
    void shouldThrowFirebaseException() {
        var vin = "1HGCM82633A123456";
        var title = "Test Title";
        var message = "Test Message";

        assertThrows(FirebaseException.class, () -> {
            throw new FirebaseException(vin, title, message);
        });
    }
}
