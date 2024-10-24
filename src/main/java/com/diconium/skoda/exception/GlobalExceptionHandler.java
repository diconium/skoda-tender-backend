package com.diconium.skoda.exception;

import com.diconium.skoda.model.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(VinNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleVinNotFoundException(final VinNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);

        return ResponseEntity.status(VinNotFoundException.getStatusCode())
                .body(new ErrorResponseDto(
                        exception.getMessage(),
                        VinNotFoundException.getStatusCode().value()));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseDto> handleException(final Throwable exception) {
        LOGGER.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
