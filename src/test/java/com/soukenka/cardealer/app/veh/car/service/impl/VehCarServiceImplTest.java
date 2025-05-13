package com.soukenka.cardealer.app.veh.car.service.impl;

import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.app.veh.car.repository.VehCarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Unit test class for {@link VehCarServiceImpl}.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 12.05.2025
 */
@ExtendWith(MockitoExtension.class)
class VehCarServiceImplTest {
    @InjectMocks
    private VehCarServiceImpl vehCarService;

    @Mock
    private VehCarRepository vehCarRepository;

    @Test
    @DisplayName("IsUniqueName returns true for unique car name")
    void testIsUniqueName_WithUniqueName() {
        VehCar vehCar = new VehCar(1L, "UniqueCar");

        Mockito.when(vehCarRepository.isUniqueName(anyLong(), anyString())).thenReturn(true);

        boolean result = vehCarService.isUniqueName(vehCar);
        assertTrue(result);
    }

    @Test
    @DisplayName("IsUniqueName returns false for duplicate car name")
    void testIsUniqueName_WithNonUniqueName() {
        VehCar vehCar = new VehCar(2L, "DuplicateCar");

        Mockito.when(vehCarRepository.isUniqueName(anyLong(), anyString())).thenReturn(false);

        boolean result = vehCarService.isUniqueName(vehCar);
        assertFalse(result);
    }

    @Test
    @DisplayName("IsUniqueName throws IllegalArgumentException for null entity")
    void testIsUniqueName_WithNullEntity() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> vehCarService.isUniqueName(null)
        );
    }
}
