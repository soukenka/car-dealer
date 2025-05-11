package com.soukenka.cardealer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuration class for validation-related beans and settings.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 10.05.2025
 */
@Configuration
public class ValidationConfig {

    /**
     * Creates and configures the validator factory bean.
     *
     * @return configured {@link LocalValidatorFactoryBean} instance
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
