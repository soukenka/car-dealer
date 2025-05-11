package com.soukenka.cardealer.core.api.error;

import com.soukenka.cardealer.core.validation.ValidationFailedException;
import jakarta.validation.ConstraintViolation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Custom error attributes for handling error responses. Extends default Spring error attributes.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    private static final String ATTRIBUTE_MESSAGE_KEY = "messageKey";
    private static final String ATTRIBUTE_VALIDATION_ERRORS = "validationErrors";

    /**
     * Extends the default error attributes with custom attributes including message key and validation errors.
     *
     * @param webRequest the source request
     * @param options    options for error attribute contents
     * @return a map of error attributes
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        // Get map of default error attributes
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        // Add custom attributes
        Throwable error = getError(webRequest);
        if (error != null) {
            errorAttributes.put(ATTRIBUTE_MESSAGE_KEY, getMessageKeyValue(error));

            List<ValidationErrorDto> validationErrors = getValidationErrors(error);
            if (CollectionUtils.isNotEmpty(validationErrors)) {
                errorAttributes.put(ATTRIBUTE_VALIDATION_ERRORS, validationErrors);
            }
        }

        return errorAttributes;
    }

    /**
     * Extracts a message key from the error's class name.
     *
     * @param error the throwable to extract a message key from
     * @return the simple class name as a message key
     */
    @NonNull
    private String getMessageKeyValue(@NonNull Throwable error) {
        Assert.notNull(error, "Parameter 'error' cannot be null");
        return error.getClass().getSimpleName();
    }

    /**
     * Extracts validation errors from {@link ValidationFailedException} if present.
     *
     * @param throwable the throwable to extract validation errors from
     * @return list of validation errors or empty list if none
     */
    @NonNull
    private List<ValidationErrorDto> getValidationErrors(Throwable throwable) {
        if (throwable instanceof ValidationFailedException exception) {
            return exception.getConstraintViolations().stream()
                    .map(this::constraintViolationToValidationErrorDto)
                    .toList();
        }
        return Collections.emptyList();
    }

    /**
     * Converts a {@link ConstraintViolation} to a {@link ValidationErrorDto}.
     *
     * @param violation {@link ConstraintViolation} to convert
     * @return {@link ValidationErrorDto} representing the validation error
     */
    @NonNull
    private ValidationErrorDto constraintViolationToValidationErrorDto(@NonNull ConstraintViolation<?> violation) {
        Assert.notNull(violation, "Parameter 'violation' cannot be null");

        String field = violation.getPropertyPath().toString();
        String messageCode = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        String defaultMessage = violation.getMessage();

        return new ValidationErrorDto(field, messageCode, defaultMessage);
    }
}
