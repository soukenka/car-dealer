package com.soukenka.cardealer.core.model;

/**
 * Base interface for entities that have an identifier.
 *
 * @param <ID> the type of the identifier
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface IdentifiableEntity<ID> {
    /**
     * Identifier of the entity.
     */
    ID getId();
}
