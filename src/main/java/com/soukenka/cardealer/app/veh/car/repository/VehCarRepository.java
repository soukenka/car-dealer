package com.soukenka.cardealer.app.veh.car.repository;

import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.core.repository.jpa.BaseJpaRepository;

/**
 * Repository interface for managing {@link VehCar} entities in the database.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface VehCarRepository extends BaseJpaRepository<VehCar, Long> {
}
