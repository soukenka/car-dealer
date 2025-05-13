package com.soukenka.cardealer.app.api.v1.veh.car;

import com.soukenka.cardealer.app.api.Endpoints;
import com.soukenka.cardealer.app.veh.car.dto.VehCarCreateDto;
import com.soukenka.cardealer.app.veh.car.dto.VehCarListDto;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.app.veh.car.service.VehCarService;
import com.soukenka.cardealer.core.api.controller.BaseRestController;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for managing {@link VehCar} operations.
 * Provides CRUD endpoints for car entities using standard REST conventions.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = Endpoints.ENDPOINT_V1_VEH_CAR)
public class VehCarController extends BaseRestController<VehCar, VehCarCreateDto, VehCarListDto, Long> {
    @Getter(AccessLevel.PROTECTED)
    private final VehCarService service;
}
