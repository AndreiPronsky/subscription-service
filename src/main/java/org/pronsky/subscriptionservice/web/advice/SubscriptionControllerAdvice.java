package org.pronsky.subscriptionservice.web.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubscriptionControllerAdvice {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong";

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException() {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
