package com.soukenka.cardealer.app.veh.car.dto;

import com.soukenka.cardealer.core.dto.BaseCreateDto;
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
public record VehCarCreateDto(
        @NotNull @NotBlank @Size(max = 255) String name
) implements BaseCreateDto {
}
