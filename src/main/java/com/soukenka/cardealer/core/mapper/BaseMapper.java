package com.soukenka.cardealer.core.mapper;

import com.soukenka.cardealer.core.dto.BaseCreateDto;
import com.soukenka.cardealer.core.model.IdentifiableEntity;
import org.mapstruct.Mapping;

/**
 * Base mapper interface providing common mapping operations between entities and DTOs.
 *
 * @param <E>  the type of the entity
 * @param <C>  the type of the creation request DTO
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface BaseMapper<E extends IdentifiableEntity<ID>, C extends BaseCreateDto, ID> {
    /**
     * Converts a DTO object to its corresponding entity representation.
     *
     * @param createDto DTO containing the data needed to create a new entity.
     * @return the entity created from the provided DTO
     */
    @Mapping(target = "id", ignore = true)
    E createDtoToEntity(C createDto);
}
