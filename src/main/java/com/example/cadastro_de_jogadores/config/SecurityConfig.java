package com.example.cadastro_de_jogadores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera o H2
                        .anyRequest().authenticated() // Resto da API exige autenticação
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Desativa CSRF para H2
                )
                .headers(headers -> headers
                        .frameOptions().disable() // Permite iframes do H2
                );
        return http.build();
    }
}