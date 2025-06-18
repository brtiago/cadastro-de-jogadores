package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.controller.LigaDaJusticaResponse;
import com.example.cadastro_de_jogadores.controller.VingadorResponse;
import com.example.cadastro_de_jogadores.service.exception.ApiExternaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class CodinomeService {

    private final WebClient webClient;

    public CodinomeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<String>> getCodinomeLigaDaJustica() {
        return comResiliencia(
                webClient.get()
                        .uri("/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml")
                        .accept(MediaType.APPLICATION_XML)
                        .retrieve()
                        .bodyToMono(LigaDaJusticaResponse.class)
                        .map(LigaDaJusticaResponse::codinomes)
        );
    }

    public Mono<List<String>> getCodinomeVingadores() {
        return comResiliencia(
                webClient.get()
                        .uri("/uolhost/test-backEnd-Java/master/referencias/vingadores.json")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(VingadorResponse.class)
                        .map(VingadorResponse::codinomes)
        );
    }

    private <T> Mono<T> comResiliencia(Mono<T> mono) {
        return mono
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(e -> {
                    log.error("Falha ao buscar codinomes da API externa: {}", e.getMessage());
                    return Mono.error(new ApiExternaException("Serviço indisponível"));
                });
    }
}
