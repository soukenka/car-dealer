package com.soukenka.cardealer.app.veh.car.mapper;

import com.soukenka.cardealer.app.veh.car.dto.VehCarCreateDto;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * Mapper interface for converting between {@link VehCar} entity and its DTOs.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Mapper(componentModel = SPRING)
public interface VehCarMapper extends BaseMapper<VehCar, VehCarCreateDto, Long> {
}
