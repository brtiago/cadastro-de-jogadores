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
                        .requestMatchers("/h2-console/**").permitAll()  // Libera o H2 Console
                        .requestMatchers("/api/v1/jogadores/**").permitAll()  // Libera sua API
                        .anyRequest().authenticated()  // Restante exige autenticação
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")  // Desativa CSRF para H2
                        .ignoringRequestMatchers("/api/v1/jogadores/**")  // Desativa CSRF para sua API
                )
                .headers(headers -> headers
                        .frameOptions().disable()  // Permite iframes do H2
                );

        return http.build();
    }
}