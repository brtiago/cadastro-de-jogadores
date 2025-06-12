package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.controller.LigaDaJusticaResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CodinomeService {

    private final WebClient webClient;

    public CodinomeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<LigaDaJusticaResponse> getCodinomeLigaDaJustica() {
        return webClient.get()
                .uri("/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml")
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(LigaDaJusticaResponse.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Falha ao buscar codinomes")));
    }

    public Mono<LigaDaJusticaResponse> getCodinomeVingadores() {
        return webClient.get()
                .uri("/uolhost/test-backEnd-Java/master/referencias/vingadores.json")
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(LigaDaJusticaResponse.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Falha ao buscar codinomes")));
    }


}
