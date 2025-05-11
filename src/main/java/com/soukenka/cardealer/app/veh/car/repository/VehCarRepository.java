package com.soukenka.cardealer.app.veh.car.repository;

import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.core.repository.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

/**
 * Repository interface for managing {@link VehCar} entities in the database.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface VehCarRepository extends BaseJpaRepository<VehCar, Long> {
    /**
     * Checks if a car name is unique in the database, excluding the specified ID (to exclude
     * the updated entity in the update).
     *
     * @param id   the ID of the car to exclude from the check (can be null for new cars)
     * @param name the name to check for uniqueness
     * @return {@code true} if the name is unique, {@code false} otherwise
     */
    @Query("""
            select (count(v) = 0)
            from VehCar v
            where (:id is null or v.id <> :id) and upper(v.name) = upper(:name)
            """)
    boolean isUniqueName(Long id, @NonNull String name);
}
