package com.soukenka.cardealer.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Base entity class providing common fields for all entities in the system.
 * Contains ID field for a primary key and version field for optimistic locking.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements IdentifiableEntity<Long> {
    /**
     * Primary key of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Version field used for optimistic locking.
     */
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public BaseEntity(@NonNull Long id) {
        Assert.notNull(id, "ID must not be null");

        this.id = id;
    }
}
