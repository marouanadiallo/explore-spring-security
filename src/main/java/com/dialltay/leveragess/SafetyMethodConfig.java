package com.dialltay.leveragess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;


@Configuration
@EnableMethodSecurity
public class SafetyMethodConfig {

    /**
     * This bean is required for Spring Security to inject the authentication object into
     * the SpEL expression in the repository.
     * -- have to add dependency spring-security-data
     * @return SecurityEvaluationContextExtension
     */
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
