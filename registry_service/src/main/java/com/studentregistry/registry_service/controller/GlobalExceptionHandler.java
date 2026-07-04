package com.studentregistry.registry_service.controller;

import com.studentregistry.registry_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Catch Custom Logic Guardrail Violations (Like Duplicate Emails/Rolls)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBusinessViolations(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(), // 409 Conflict status
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Catch Validation Failure Violations (Like Malformed Email Syntaxes via @Email)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationFailures(MethodArgumentNotValidException ex) {
        // Collect all distinct annotation violation messages into a clean, comma-separated string
        String combinedErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), // 400 Bad Request status
                combinedErrors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}