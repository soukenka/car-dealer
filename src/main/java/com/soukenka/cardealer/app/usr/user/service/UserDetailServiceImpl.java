package com.soukenka.cardealer.app.usr.user.service;

import com.soukenka.cardealer.app.usr.user.repository.UsrUserRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Implementation of {@link UserDetailsService} that loads user-specific data from the {@link UsrUserRepository}.
 * This service is used by Spring Security for user authentication and authorization.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 09.05.2025
 */
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Getter(AccessLevel.PROTECTED)
    private final UsrUserRepository repository;

    /**
     * Locates the user based on the username.
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated {@link UserDetails} object
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Assert.notNull(username, "Parameter 'username' cannot be null");

        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
    }
}
