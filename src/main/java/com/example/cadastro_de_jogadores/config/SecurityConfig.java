package com.example.cadastro_de_jogadores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/swagger.yaml",          // Libera o acesso ao YAML
            "/swagger-resources/**",
            "/webjars/**",
            "/error"
    };


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests(auth -> auth
                              .requestMatchers(SWAGGER_WHITELIST).permitAll()
                              .requestMatchers("/h2-console/**").permitAll()
                              .requestMatchers("/api/v1/jogadores/**").permitAll()
                              .requestMatchers("/", "/index", "/home").permitAll()  // Páginas principais
                              .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll() // Recursos estáticos

                      .anyRequest().authenticated()
              )
              .csrf(csrf -> csrf
                              .ignoringRequestMatchers("/h2-console/**")
                              .ignoringRequestMatchers("/api/v1/jogadores/**")
                              .ignoringRequestMatchers(SWAGGER_WHITELIST)  // Desativa CSRF para Swagger
              )
              .headers(headers -> headers
                              .frameOptions().disable()
              );

      return http.build();
    }

}