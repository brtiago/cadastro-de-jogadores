package com.example.cadastro_de_jogadores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] WHITELISTED_API_ENDPOINTS = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/swagger.yaml",          // Libera o acesso ao YAML
            "/swagger-resources/**",
            "/webjars/**",
            "/error",
            "/h2-console/**",
            "/api/v1/jogadores/**",
            "/", "/index", "/home",
            "/static/**", "/css/**", "/js/**", "/images/**"
    };


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests(auth -> auth
                              .requestMatchers(WHITELISTED_API_ENDPOINTS).permitAll()
                      .anyRequest().authenticated()
              )
              .csrf(csrf -> csrf
                              .ignoringRequestMatchers(WHITELISTED_API_ENDPOINTS)  // Desativa CSRF para Swagger
              )
              .headers(headers -> headers
                              .frameOptions().disable()
              );

      return http.build();
    }

}