package com.soukenka.cardealer.app.veh.car.service.impl;

import com.soukenka.cardealer.app.veh.car.dto.VehCarCreateDto;
import com.soukenka.cardealer.app.veh.car.dto.VehCarListDto;
import com.soukenka.cardealer.app.veh.car.mapper.VehCarMapper;
import com.soukenka.cardealer.app.veh.car.model.VehCar;
import com.soukenka.cardealer.app.veh.car.repository.VehCarRepository;
import com.soukenka.cardealer.app.veh.car.service.VehCarService;
import com.soukenka.cardealer.core.service.impl.BaseRestServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Service for managing {@link VehCar} entities in the system.
 * Provides CRUD operations and additional functionality for vehicle cars.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public class VehCarServiceImpl
        extends BaseRestServiceImpl<VehCar, VehCarCreateDto, VehCarListDto, Long>
        implements VehCarService {

    private final VehCarRepository repository;
    private final VehCarMapper mapper;

    @Override
    @NonNull
    protected Class<VehCarListDto> getListDtoClass() {
        return VehCarListDto.class;
    }

    @Override
    public boolean isUniqueName(@NonNull VehCar entity) {
        Assert.notNull(entity, "Parameter 'entity' cannot be null");
        
        return repository.isUniqueName(entity.getId(), entity.getName());
    }
}
