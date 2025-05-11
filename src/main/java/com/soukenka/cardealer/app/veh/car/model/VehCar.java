package com.soukenka.cardealer.app.veh.car.model;

import com.soukenka.cardealer.core.model.BaseCreatableEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing a vehicle car in the system.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "veh_car")
@AttributeOverride(name = "id", column = @Column(name = "id_veh_car", nullable = false))
public class VehCar extends BaseCreatableEntity {
    /**
     * The name of the vehicle car.
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
