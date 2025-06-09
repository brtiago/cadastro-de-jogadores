package com.example.cadastro_de_jogadores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webclient() {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));

        return WebClient.builder()
                .baseUrl("https://raw.githubusercontent.com")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
