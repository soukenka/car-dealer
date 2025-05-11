package com.soukenka.cardealer.core.api.wrapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * A wrapper record class for create request payloads. It encapsulates the data
 * being sent in creation requests to ensure a consistent request structure.
 *
 * @param <T> the type of data being wrapped in the creation request
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
public record CreateRequest<T>(@Valid @NotNull T data) {
}
