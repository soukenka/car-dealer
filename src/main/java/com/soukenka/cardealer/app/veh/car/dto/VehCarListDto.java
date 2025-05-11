package com.soukenka.cardealer.app.veh.car.dto;

import com.soukenka.cardealer.core.dto.BaseListDto;
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
public record VehCarListDto(
        @NotNull Long id,
        @NotNull Long version,
        @NotNull Instant createdDate,
        @NotNull @NotBlank @Size(max = 255) String name
) implements BaseListDto {
}
