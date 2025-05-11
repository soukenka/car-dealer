package com.soukenka.cardealer.app.veh.car.dto;

import com.soukenka.cardealer.core.dto.BaseListDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * DTO for {@link com.soukenka.cardealer.app.veh.car.model.VehCar} for use in the list.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Schema(description = "Car DTO for list")
public record VehCarListDto(
        @Schema(description = "Primary key", example = "1")
        @NotNull Long id,
        @Schema(description = "Version for optimistic locking", example = "0")
        @NotNull Long version,
        @Schema(description = "Creation timestamp", example = "2024-01-01T00:00:00Z")
        @NotNull Instant createdDate,
        @Schema(description = "Car name", example = "Toyota Camry")
        @NotNull @NotBlank @Size(max = 255) String name
) implements BaseListDto {
}
