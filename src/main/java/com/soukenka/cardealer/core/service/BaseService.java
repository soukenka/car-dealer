package com.soukenka.cardealer.core.service;

import com.soukenka.cardealer.core.model.IdentifiableEntity;
import org.springframework.lang.NonNull;

/**
 * Base service interface that defines common operations for entity management.
 *
 * @param <E>  the type of the entity
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface BaseService<E extends IdentifiableEntity<ID>, ID> {
    /**
     * Saves the given entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @NonNull
    E save(@NonNull E entity);
}
