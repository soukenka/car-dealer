package com.soukenka.cardealer.core.repository.jpa;

import com.soukenka.cardealer.core.model.IdentifiableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

/**
 * Base JPA repository interface with additional projection functionality.
 *
 * @param <E>  the type of the entity
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@NoRepositoryBean
public interface BaseJpaRepository<E extends IdentifiableEntity<ID>, ID> extends JpaRepository<E, ID> {
    /**
     * Retrieves all entities as projections of the specified type with pagination and sorting support.
     *
     * @param pageable       pagination information
     * @param projectionType the class of the projection type
     * @param <P>            the type of the projection
     * @return a {@link Page} of projected results
     */
    @NonNull
    <P> Page<P> getProjectedBy(@NonNull Pageable pageable, @NonNull Class<P> projectionType);
}
