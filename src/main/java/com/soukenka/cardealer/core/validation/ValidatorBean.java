package com.soukenka.cardealer.core.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * Utility component for performing bean validation using a Hibernate Validator.
 * Provides centralized validation functionality for beans.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@RequiredArgsConstructor
@Component
public class ValidatorBean {
    private final Validator validator;

    /**
     * Validates the given bean against its defined constraints.
     *
     * @param target the bean to validate
     * @param <T>    the type of the bean to validate
     * @throws ValidationFailedException if any constraints are violated
     */
    public <T> void validate(@NonNull T target) {
        Assert.notNull(target, "Parameter 'target' cannot be null");

        Set<ConstraintViolation<T>> violations = validator.validate(target);
        if (!violations.isEmpty()) {
            throw new ValidationFailedException(violations);
        }
    }
}
