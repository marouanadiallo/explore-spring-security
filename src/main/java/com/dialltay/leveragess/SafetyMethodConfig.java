package com.dialltay.leveragess;

import com.dialltay.leveragess.document.DocumentsPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SafetyMethodConfig {
    private final DocumentsPermissionEvaluator docPermissionEvaluator;

    public SafetyMethodConfig(DocumentsPermissionEvaluator docPermissionEvaluator) {
        this.docPermissionEvaluator = docPermissionEvaluator;
    }

    /**
     * To make Spring security aware of our custom permission evaluator
     *
     * @return a MethodSecurityExpressionHandler
     */
    @Bean
    MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(docPermissionEvaluator);
        return expressionHandler;
    }
}
