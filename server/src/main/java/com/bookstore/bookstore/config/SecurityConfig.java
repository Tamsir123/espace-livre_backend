package com.bookstore.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Désactive la protection CSRF (Cross-Site Request Forgery)
            .authorizeHttpRequests()
            .anyRequest().permitAll(); // Autorise toutes les requêtes sans authentification
        return http.build();
    }
}
