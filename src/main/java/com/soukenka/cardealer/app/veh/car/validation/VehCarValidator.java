package com.soukenka.cardealer.app.veh.car.validation;

import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.app.veh.car.service.VehCarService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Validator for {@link VehCar} entities to validate them before persistence operations.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@RequiredArgsConstructor
@Component
public class VehCarValidator implements ConstraintValidator<VehCarConstraint, VehCar> {
    private final VehCarService service;

    @Override
    public boolean isValid(@NonNull VehCar entity, @NonNull ConstraintValidatorContext context) {
        Assert.notNull(entity, "Parameter 'entity' cannot be null");
        Assert.notNull(context, "Parameter 'context' cannot be null");

        boolean isValid = true;

        // Check uniqueness using a DB query
        if (!service.isUniqueName(entity)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{veh.car.name.exists}").addConstraintViolation();

            isValid = false;
        }
        return isValid;
    }
}
