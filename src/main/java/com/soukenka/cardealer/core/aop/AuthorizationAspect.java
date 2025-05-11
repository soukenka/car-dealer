package com.soukenka.cardealer.core.aop;

import com.soukenka.cardealer.core.security.SecurityService;
import com.soukenka.cardealer.core.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * AOP aspect for handling authorization checks in controller methods.
 * Validates if the current user has appropriate roles for reading or writing operations.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationAspect {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLE_SUFFIX_WRITE = "_WRITE";
    private static final String ROLE_SUFFIX_READ = "_READ";

    private static final String CONTROLLER_SUFFIX = "Controller";

    private final SecurityService securityService;

    /**
     * Checks if the user has read permission to proceed with the read operation.
     *
     * @param joinPoint the join point representing the intercepted method
     */
    @Before("execution(* com.soukenka.cardealer.core.api.controller.*.getAll(..))")
    public void authorizeRead(JoinPoint joinPoint) {
        authorize(joinPoint, ROLE_SUFFIX_READ);
    }

    /**
     * Checks if the user has write permission to proceed with the modification operation.
     *
     * @param joinPoint the join point representing the intercepted method
     */
    @Before("execution(* com.soukenka.cardealer.core.api.controller.*.create(..))")
    public void authorizeWrite(JoinPoint joinPoint) {
        authorize(joinPoint, ROLE_SUFFIX_WRITE);
    }

    /**
     * Performs the authorization check for a given operation.
     *
     * @param joinPoint  the join point representing the intercepted method
     * @param roleSuffix the role suffix (for read or write) to check
     * @throws AccessDeniedException if the user doesn't have the required role
     */
    public void authorize(@NonNull JoinPoint joinPoint, @NonNull String roleSuffix) {
        Assert.notNull(joinPoint, "Parameter 'joinPoint' cannot be null");
        Assert.notNull(roleSuffix, "Parameter 'roleSuffix' cannot be null");

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String role = getRoleName(className, roleSuffix);

        if (!securityService.hasRole(role)) {
            log.info("Authorization failed. User does not have the role {} for operation in class {}.", role, className);
            throw new AccessDeniedException("User not authorized to access this resource");
        }
    }

    /**
     * Constructs the role name based on the given class name and role suffix.
     *
     * @param className  the name of the controller class
     * @param roleSuffix the role suffix (for read or write)
     * @return the constructed role name used for authorization checks
     */
    @NonNull
    private String getRoleName(@NonNull String className, @NonNull String roleSuffix) {
        Assert.notNull(className, "Parameter 'className' cannot be null");
        Assert.notNull(roleSuffix, "Parameter 'roleSuffix' cannot be null");

        String snakeModelName = StringUtils.convertCamelToSnake(StringUtils.removeSuffix(className, CONTROLLER_SUFFIX));
        return ROLE_PREFIX + snakeModelName.toUpperCase() + roleSuffix;
    }
}
