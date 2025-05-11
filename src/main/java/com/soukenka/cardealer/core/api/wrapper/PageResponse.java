package com.soukenka.cardealer.core.api.wrapper;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * A wrapper record class for paginated data responses. It encapsulates the data collection
 * along with pagination metadata. This class is designed to provide a standardized format
 * for returning paginated data from API endpoints.
 *
 * @param <T> the type of elements in the data collection
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public record PageResponse<T>(
        @NotNull Collection<T> data,

        @NotNull int pageNumber,

        @NotNull int pageSize,

        @NotNull long totalElements,

        @NotNull int totalPages) {

    /**
     * Creates a new {@link PageResponse} instance from a {@link Page} object.
     * This factory method extracts all necessary pagination information from the provided page.
     *
     * @param page the Spring Data Page object containing the data and pagination information
     * @param <E>  the type of elements in the page
     * @return a new {@link PageResponse} instance containing the page data and metadata
     */
    @NonNull
    public static <E> PageResponse<E> of(@NonNull Page<E> page) {
        Assert.notNull(page, "Parameter 'page' cannot be null");

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
