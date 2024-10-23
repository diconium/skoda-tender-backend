package com.diconium.skoda.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VinNotFoundExceptionTest {

    @Test
    void constructor_shouldSetCorrectMessage() {
        var vin = "1HGCM82633A123456";
        var exception = new VinNotFoundException(vin);

        assertEquals("No car found for VIN: " + vin, exception.getMessage());
    }

    @Test
    void getStatusCode_shouldReturnNotFoundStatus() {
        assertEquals(HttpStatus.NOT_FOUND, VinNotFoundException.getStatusCode());
    }

    @Test
    void shouldThrowVinNotFoundException() {
        var vin = "1HGCM82633A123456";

        assertThrows(VinNotFoundException.class, () -> {
            throw new VinNotFoundException(vin);
        });
    }
}
