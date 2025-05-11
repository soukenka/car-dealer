package com.soukenka.cardealer.core.service.impl;

import com.soukenka.cardealer.core.dto.BaseCreateDto;
import com.soukenka.cardealer.core.dto.BaseListDto;
import com.soukenka.cardealer.core.mapper.BaseMapper;
import com.soukenka.cardealer.core.model.IdentifiableEntity;
import com.soukenka.cardealer.core.repository.jpa.BaseJpaRepository;
import com.soukenka.cardealer.core.service.BaseRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Base REST service providing common CRUD operations.
 *
 * @param <E>  the type of the entity
 * @param <C>  the type of the creation request DTO
 * @param <L>  the type of the list response DTO
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public abstract class BaseRestServiceImpl<E extends IdentifiableEntity<ID>, C extends BaseCreateDto, L extends BaseListDto, ID>
        extends BaseServiceImpl<E, ID>
        implements BaseRestService<E, C, L, ID> {
    @NonNull
    protected abstract BaseMapper<E, C, ID> getMapper();

    @NonNull
    protected abstract BaseJpaRepository<E, ID> getRepository();

    @Override
    @NonNull
    public E create(@NonNull C createDto) {
        Assert.notNull(createDto, "DTO must not be null");

        E entity = getMapper().createDtoToEntity(createDto);

        Assert.notNull(entity, "Entity must not be null");
        Assert.isNull(entity.getId(), "Entity Id must be null");

        return save(entity);
    }

    /**
     * Returns the class type of the list response DTO used for projections.
     *
     * @return the class representing the list response DTO type
     */
    @NonNull
    protected abstract Class<L> getListDtoClass();

    @Override
    @NonNull
    public Page<L> getAll(@NonNull Pageable pageable) {
        Assert.notNull(pageable, "Parameter 'pageable' cannot be null");

        return getRepository().getProjectedBy(pageable, getListDtoClass());
    }
}
