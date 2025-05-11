package com.soukenka.cardealer.core.api.error;

/**
 * Validation error structure in REST API responses.
 * Used to transfer validation error information from backend to client.
 *
 * @param field          field name
 * @param messageCode    error message code
 * @param defaultMessage default error message
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
public record ValidationErrorDto(String field, String messageCode, String defaultMessage) {
}
