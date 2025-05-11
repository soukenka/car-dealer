package com.soukenka.cardealer.app.veh.car.service;

import com.soukenka.cardealer.app.veh.car.dto.VehCarCreateDto;
import com.soukenka.cardealer.app.veh.car.dto.VehCarListDto;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.core.service.BaseRestService;

/**
 * Service interface for managing {@link VehCar} entities in the system.
 * Provides CRUD operations and additional functionality for vehicle cars.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface VehCarService extends BaseRestService<VehCar, VehCarCreateDto, VehCarListDto, Long> {
    /**
     * Checks if the given car name is unique in the database.
     *
     * @param entity The vehicle car entity to check
     * @return {@code true} if the name is unique, {@code false} otherwise
     */
    boolean isUniqueName(VehCar entity);
}
