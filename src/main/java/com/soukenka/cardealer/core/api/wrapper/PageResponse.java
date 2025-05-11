package com.soukenka.cardealer.core.api.wrapper;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Response wrapper for paginated data")
public record PageResponse<T>(
        @Schema(description = "Collection of data items for current page")
        @NotNull Collection<T> data,

        @Schema(description = "Current page number (0-based)", example = "0")
        @NotNull int pageNumber,

        @Schema(description = "Maximum number of items per page", example = "20")
        @NotNull int pageSize,

        @Schema(description = "Total number of items across all pages", example = "105")
        @NotNull long totalElements,

        @Schema(description = "Total number of pages", example = "6")
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
