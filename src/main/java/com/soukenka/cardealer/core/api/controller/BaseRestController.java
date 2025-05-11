package com.soukenka.cardealer.core.api.controller;

import com.soukenka.cardealer.core.api.wrapper.CreateRequest;
import com.soukenka.cardealer.core.api.wrapper.PageResponse;
import com.soukenka.cardealer.core.dto.BaseCreateDto;
import com.soukenka.cardealer.core.dto.BaseListDto;
import com.soukenka.cardealer.core.model.IdentifiableEntity;
import com.soukenka.cardealer.core.service.BaseRestService;
import com.soukenka.cardealer.core.validation.ValidatorBean;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Base controller providing common REST endpoints for CRUD operations.
 *
 * @param <E>  the type of the entity
 * @param <C>  the type of the creation request DTO
 * @param <L>  the type of the list response DTO
 * @param <ID> the type of the entity's identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public abstract class BaseRestController<E extends IdentifiableEntity<ID>, C extends BaseCreateDto, L extends BaseListDto, ID> {
    private final Logger log = LoggerFactory.getLogger(super.getClass());

    @Setter(onMethod_ = @Autowired)
    private ValidatorBean validator;

    protected abstract BaseRestService<E, C, L, ID> getService();

    /**
     * Creates a new entity from the provided data.
     *
     * @param createWrapper the wrapped creation request containing DTO data
     * @return {@link ResponseEntity} with a location header pointing to the created resource
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateRequest<C> createWrapper) {
        validator.validate(createWrapper);

        IdentifiableEntity<ID> entity = getService().create(createWrapper.data());
        return getCreateResponse(entity.getId());
    }

    /**
     * Creates a response for successful entity creation.
     *
     * @param id identifier of the created entity
     * @return {@link ResponseEntity} with location header
     */
    @NonNull
    private ResponseEntity<Void> getCreateResponse(@NonNull ID id) {
        Assert.notNull(id, "Entity Id must not be null");
        return ResponseEntity.created(getDetailLocationAfterCreate(id)).build();
    }

    /**
     * Builds the URI for accessing the newly created resource.
     *
     * @param id identifier of the created entity
     * @return URI pointing to the created resource
     */
    @NonNull
    private URI getDetailLocationAfterCreate(@NonNull ID id) {
        Assert.notNull(id, "Entity Id must not be null");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .normalize()
                .toUri();
        return URI.create(uri.getPath());
    }

    /**
     * Retrieves a paginated list of all entities.
     *
     * @param pageable pagination and sorting parameters
     * @return {@link ResponseEntity} containing page of entities
     */
    @GetMapping
    public ResponseEntity<PageResponse<L>> getAll(
            @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        log.info("Getting all records");
        Page<L> result = getService().getAll(pageable);

        log.debug("Found {} records", result.getTotalElements());
        return ResponseEntity.ok().body(PageResponse.of(result));
    }
}
