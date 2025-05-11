package com.soukenka.cardealer.core.security.impl;

import com.soukenka.cardealer.core.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Service providing security-related operations for managing user roles and permissions.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    @NonNull
    @Override
    public Set<String> getLoggedUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        }
        return Collections.emptySet();
    }

    @Override
    public boolean hasRole(String role) {
        return getLoggedUserRoles().contains(role);
    }
}
