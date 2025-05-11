package com.soukenka.cardealer.core.security;

import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * Service interface providing security-related operations for managing user roles and permissions.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
public interface SecurityService {
    /**
     * Retrieves the set of roles assigned to the currently logged-in user.
     *
     * @return a non-null set of role names
     */
    @NonNull
    Set<String> getLoggedUserRoles();

    /**
     * Checks if the currently logged-in user has the specified role.
     *
     * @param role the role name to check
     * @return {@code true} if the logged-in user has the role, {@code false} otherwise.
     */
    boolean hasRole(String role);
}
