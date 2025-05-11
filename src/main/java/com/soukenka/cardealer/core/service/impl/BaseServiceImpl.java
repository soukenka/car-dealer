package com.soukenka.cardealer.core.service.impl;

import com.soukenka.cardealer.core.model.IdentifiableEntity;
import com.soukenka.cardealer.core.service.BaseService;
import com.soukenka.cardealer.core.validation.ValidatorBean;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Base service that defines common operations for entity management.
 *
 * @param <E>  the type of the entity
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public abstract class BaseServiceImpl<E extends IdentifiableEntity<ID>, ID>
        implements BaseService<E, ID> {
    @Getter(AccessLevel.PROTECTED)
    @Setter(onMethod_ = @Autowired)
    private ValidatorBean validator;

    @NonNull
    protected abstract JpaRepository<E, ID> getRepository();

    @Override
    @NonNull
    public E save(@NonNull E entity) {
        Assert.notNull(entity, "Entity must not be null");

        validator.validate(entity);
        return getRepository().save(entity);
    }
}
