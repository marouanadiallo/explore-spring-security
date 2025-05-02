package com.dialltay.leveragess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
class SafeAspectsConfig {

    @Bean
    SecurityFilterChain defineSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authz -> {
            authz.anyRequest().authenticated();
        });

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // In-memory user details manager for demonstration purposes
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        var service = new InMemoryUserDetailsManager();

        var usr1 = User.withUsername("alphamar")
                .password(passwordEncoder.encode("mar123"))
                //.authorities("read", "write")
                .roles("admin")
                .build();

        var usr2 = User.withUsername("betamar")
                .password(passwordEncoder.encode("mar321"))
                .roles("manager")
                //.authorities("read")
                .build();

        service.createUser(usr1);
        service.createUser(usr2);
        return service;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
