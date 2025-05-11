package com.soukenka.cardealer.app.veh.car.validation;

import com.soukenka.cardealer.app.veh.car.model.VehCar;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation constraint annotation for {@link VehCar} validation.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Constraint(validatedBy = VehCarValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VehCarConstraint {
    String message() default "{veh.car.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
