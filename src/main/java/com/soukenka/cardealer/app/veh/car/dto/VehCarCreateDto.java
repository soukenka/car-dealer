package com.soukenka.cardealer.app.veh.car.dto;

import com.soukenka.cardealer.core.dto.BaseCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link com.soukenka.cardealer.app.veh.car.model.VehCar} for use in the create operation.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Schema(description = "Car DTO for creation")
public record VehCarCreateDto(
        @Schema(description = "Car name", example = "Toyota Camry")
        @NotNull @NotBlank @Size(max = 255) String name
) implements BaseCreateDto {
}
