package com.soukenka.cardealer.app.api.v1.veh.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soukenka.cardealer.TestcontainersConfiguration;
import com.soukenka.cardealer.app.veh.car.dto.VehCarCreateDto;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.app.veh.car.repository.VehCarRepository;
import com.soukenka.cardealer.core.api.wrapper.CreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.soukenka.cardealer.app.api.Endpoints.ENDPOINT_V1_VEH_CAR;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test class for the car REST API implemented in {@link VehCarController}.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 12.05.2025
 */
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
@Transactional
@Import(TestcontainersConfiguration.class)
class VehCarControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VehCarRepository carRepository;

    @Test
    @DisplayName("PATCH /api/v1/veh/cars - Returns method not allowed")
    @WithMockUser
    void testRequestWithPatch_ReturnsMethodNotAllowed() throws Exception {
        mockMvc.perform(patch(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("GET /api/v1/veh/cars - Returns empty list when no vehicles exist")
    @WithMockUser(roles = {"VEH_CAR_READ"})
    void testGetAll_ReturnsEmptyPage_WhenNoVehiclesExist() throws Exception {
        mockMvc.perform(get(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)))
                .andExpect(jsonPath("$.totalElements").value(0));
    }

    @Test
    @DisplayName("GET /api/v1/veh/cars - Returns list of vehicles when they exist")
    @WithMockUser(roles = {"VEH_CAR_READ"})
    void testGetAll_ReturnsVehicles_WhenTheyExist() throws Exception {
        var cars = List.of(
                new VehCar("Škoda Octavia"),
                new VehCar("BMW X5")
        );
        carRepository.saveAll(cars);

        mockMvc.perform(get(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.data[*].name", containsInAnyOrder("Škoda Octavia", "BMW X5")));
    }

    @Test
    @DisplayName("GET /api/v1/veh/cars - Returns forbidden when user has only WRITE role")
    @WithMockUser(roles = {"VEH_CAR_WRITE"})
    void testGetAll_ReturnsForbidden_WhenUserHasOnlyWriteRole() throws Exception {
        mockMvc.perform(get(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GET /api/v1/veh/cars - Returns unauthorized when user is not authenticated")
    void testGetAll_ReturnsUnauthorized_WhenUserIsNotAuthenticated() throws Exception {
        mockMvc.perform(get(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Creates new vehicle")
    @WithMockUser(roles = {"VEH_CAR_WRITE"})
    void testCreate_ReturnsCreated_WhenValidDataProvided() throws Exception {
        VehCarCreateDto createDto = new VehCarCreateDto("Audi A4");
        CreateRequest<VehCarCreateDto> createRequest = new CreateRequest<>(createDto);

        MvcResult result = mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assertNotNull(location, "Location header should exist");

        // Verify that the vehicle was saved in a database
        List<VehCar> savedCars = carRepository.findAll();
        assertEquals(1, savedCars.size(), "There should be one vehicle in database");
        VehCar savedCar = savedCars.getFirst();
        assertEquals("Audi A4", savedCar.getName(), "Saved vehicle should have correct name");
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Returns error for invalid data")
    @WithMockUser(roles = {"VEH_CAR_WRITE"})
    void testCreate_ReturnsBadRequest_WhenInvalidDataProvided() throws Exception {
        VehCarCreateDto invalidDto = new VehCarCreateDto(null);
        CreateRequest<VehCarCreateDto> createRequest = new CreateRequest<>(invalidDto);

        mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());

        // Verify that no vehicle was saved
        assertEquals(0, carRepository.count(), "There should be no vehicles in database");
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Returns bad request when car name is too long")
    @WithMockUser(roles = {"VEH_CAR_WRITE"})
    void testCreate_ReturnsBadRequest_WhenNameIsTooLong() throws Exception {
        // Create a name with 256 characters (assuming limit is 255)
        String tooLongName = "A".repeat(256);
        VehCarCreateDto invalidDto = new VehCarCreateDto(tooLongName);
        CreateRequest<VehCarCreateDto> createRequest = new CreateRequest<>(invalidDto);

        mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());

        // Verify that no vehicle was saved
        assertEquals(0, carRepository.count(), "There should be no vehicles in database");
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Returns bad request for invalid JSON format")
    @WithMockUser(roles = {"VEH_CAR_WRITE"})
    void testCreate_ReturnsBadRequest_WhenJsonIsInvalid() throws Exception {
        String invalidJson = "{\"data\": {\"name\": \"Audi A4\""; // missing closing braces

        mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

        // Verify that no vehicle was saved
        assertEquals(0, carRepository.count(), "There should be no vehicles in database");
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Returns forbidden when user has only READ role")
    @WithMockUser(roles = {"VEH_CAR_READ"})
    void testCreate_ReturnsForbidden_WhenUserHasOnlyReadRole() throws Exception {
        VehCarCreateDto createDto = new VehCarCreateDto("Audi A4");
        CreateRequest<VehCarCreateDto> createRequest = new CreateRequest<>(createDto);

        mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isForbidden());

        // Verify that no vehicle was saved
        assertEquals(0, carRepository.count(), "There should be no vehicles in database");
    }

    @Test
    @DisplayName("POST /api/v1/veh/cars - Returns unauthorized when user is not authenticated")
    void testCreate_ReturnsUnauthorized_WhenUserIsNotAuthenticated() throws Exception {
        VehCarCreateDto createDto = new VehCarCreateDto("Audi A4");
        CreateRequest<VehCarCreateDto> createRequest = new CreateRequest<>(createDto);

        mockMvc.perform(post(ENDPOINT_V1_VEH_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isUnauthorized());

        // Verify that no vehicle was saved
        assertEquals(0, carRepository.count(), "There should be no vehicles in database");
    }
}
