package com.soukenka.cardealer.app.veh.car.repository;

import com.soukenka.cardealer.TestcontainersConfiguration;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test class for testing the {@link VehCarRepository}.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 12.05.2025
 */
@DataJpaTest
@Rollback
@Transactional
@Import(TestcontainersConfiguration.class)
class VehCarRepositoryIT {
    @Autowired
    private VehCarRepository vehCarRepository;

    @ParameterizedTest
    @CsvSource({
            "Audi A4, Škoda Octavia, true",
            "Škoda Octavia, ŠKODA OCTAVIA, false"
    })
    @DisplayName("Verify name uniqueness under different scenarios")
    void testIsUniqueName_VerifyUniqueness(String existingCarName, String nameToCheck, boolean expectedResult) {
        VehCar vehCar = new VehCar(existingCarName);
        vehCarRepository.save(vehCar);

        boolean isUnique = vehCarRepository.isUniqueName(null, nameToCheck);
        assertThat(isUnique).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Return true when repository is empty")
    void testIsUniqueName_ReturnTrue_WhenRepositoryIsEmpty() {
        boolean isUnique = vehCarRepository.isUniqueName(null, "Škoda Octavia");
        assertThat(isUnique).isTrue();
    }

    @Test
    @DisplayName("Return true when car with the same name exists but id is excluded")
    void testIsUniqueName_ReturnTrue_WhenCarWithSameNameExistsButIdIsExcluded() {
        VehCar vehCar = new VehCar("Škoda Octavia");
        vehCar = vehCarRepository.save(vehCar);

        boolean isUnique = vehCarRepository.isUniqueName(vehCar.getId(), "Škoda Octavia");
        assertThat(isUnique).isTrue();
    }

    @Test
    @DisplayName("Return true when name is null")
    void testIsUniqueName_ReturnTrue_WhenNameIsNull() {
        boolean isUnique = vehCarRepository.isUniqueName(null, null);
        assertThat(isUnique).isTrue();
    }
}
