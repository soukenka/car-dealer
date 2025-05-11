package com.soukenka.cardealer.core.model;

import java.time.Instant;

/**
 * Interface representing an entity that has a creation timestamp.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface CreatableEntity {
    /**
     * Timestamp when the entity was created.
     */
    Instant getCreatedDate();
}
