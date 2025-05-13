package com.soukenka.cardealer.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import java.time.Instant;

/**
 * Base abstract class for entities that need creation timestamp tracking.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseCreatableEntity extends BaseEntity implements CreatableEntity {
    /**
     * Timestamp when the entity was created.
     * This field is automatically set during entity creation and cannot be updated.
     */
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    public BaseCreatableEntity(@NonNull Long id) {
        super(id);
    }
}
