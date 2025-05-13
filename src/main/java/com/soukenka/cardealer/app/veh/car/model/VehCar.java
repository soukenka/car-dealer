package com.soukenka.cardealer.app.veh.car.model;

import com.soukenka.cardealer.app.veh.car.validation.VehCarConstraint;
import com.soukenka.cardealer.core.model.BaseCreatableEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veh_car")
@AttributeOverride(name = "id", column = @Column(name = "id_veh_car", nullable = false))
@VehCarConstraint
public class VehCar extends BaseCreatableEntity {
    /**
     * The name of the vehicle car.
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public VehCar(@NonNull Long id, @NonNull String name) {
        super(id);
        Assert.notNull(name, "Name must not be null");

        this.name = name;
    }
}
