package com.soukenka.cardealer.app.usr.user.repository;

import com.soukenka.cardealer.app.usr.user.model.UsrUser;
import com.soukenka.cardealer.core.repository.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * Repository interface for managing {@link UsrUser} entities in the database.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
public interface UsrUserRepository extends BaseJpaRepository<UsrUser, Long> {
    @NonNull
    @EntityGraph(attributePaths = {"usrRoles"})
    Optional<UsrUser> findByUsername(@NonNull String username);
}
