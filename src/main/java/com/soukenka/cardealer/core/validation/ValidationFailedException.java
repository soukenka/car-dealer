package com.soukenka.cardealer.core.validation;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

/**
 * Exception thrown when validation of data fails, typically during request processing.
 * Returns HTTP 400 (Bad Request) status code and contains a set of constraint violations
 * that caused the validation failure.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
public class ValidationFailedException extends ResponseStatusException {
    private final Set<ConstraintViolation<?>> constraintViolations;

    public ValidationFailedException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this("Validation failed", constraintViolations);
    }

    public ValidationFailedException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(HttpStatus.BAD_REQUEST, message, null);
        this.constraintViolations = new HashSet<>(constraintViolations);
    }
}
