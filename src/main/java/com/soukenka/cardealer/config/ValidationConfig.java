package com.soukenka.cardealer.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

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
     * Represents the default locale used for validation-related configurations.
     */
    private static final Locale DEFAULT_LOCALE = Locale.of("cs", "CZ");

    /**
     * Creates and configures the validator factory bean.
     *
     * @param validationMessageSource the message source for validation messages
     * @return configured {@link LocalValidatorFactoryBean} instance
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource validationMessageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessageSource);
        return bean;
    }

    /**
     * Creates and configures the message source for validation messages.
     * Uses "ValidationMessages" resource bundle.
     *
     * @return configured {@link ResourceBundleMessageSource} instance
     */
    @Bean
    public ResourceBundleMessageSource validationMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/ValidationMessages");
        messageSource.setDefaultLocale(DEFAULT_LOCALE);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setAlwaysUseMessageFormat(true);
        return messageSource;
    }
}
