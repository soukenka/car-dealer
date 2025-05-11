package com.soukenka.cardealer.core.service;

import com.soukenka.cardealer.core.dto.BaseCreateDto;
import com.soukenka.cardealer.core.dto.BaseListDto;
import com.soukenka.cardealer.core.model.IdentifiableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * Base interface for REST services providing common CRUD operations.
 *
 * @param <E>  the type of the entity
 * @param <C>  the type of the creation request DTO
 * @param <L>  the type of the list response DTO
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface BaseRestService<E extends IdentifiableEntity<ID>, C extends BaseCreateDto, L extends BaseListDto, ID>
        extends BaseService<E, ID> {
    /**
     * Creates a new entity from the provided DTO.
     *
     * @param createDTO the DTO containing creation data
     * @return the created entity
     */
    @NonNull
    E create(@NonNull C createDTO);

    /**
     * Retrieves all entities as DTOs with pagination.
     *
     * @param pageable pagination and sorting information
     * @return page of DTOs
     */
    @NonNull
    Page<L> getAll(@NonNull Pageable pageable);
}
