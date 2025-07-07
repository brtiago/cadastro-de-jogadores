package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.dto.response.LigaDaJusticaResponse;
import com.example.cadastro_de_jogadores.repository.JogadorRepository;
import com.example.cadastro_de_jogadores.exception.ApiExternaException;
import com.example.cadastro_de_jogadores.exception.CodinomeIndisponivelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Slf4j
public class LigaCodinomeStrategy implements CodinomeStrategy {
    private final WebClient webClient;
    private final JogadorRepository jogadorRepository;

    public LigaCodinomeStrategy(WebClient webClient, JogadorRepository jogadorRepository) {
        this.webClient = webClient;
        this.jogadorRepository = jogadorRepository;
    }

    public Mono<String> obterCodinomeDisponivel() {
        return getCodinomeLigaDaJustica()
                .flatMap(codinomes -> {
                    Set<String> codinomesUsados = jogadorRepository.findCodinomeByGrupoNome("Liga da Justiça");
                    List<String> disponiveis = codinomes.stream()
                            .filter(c -> !codinomesUsados.contains(c))
                            .toList();
                    if(disponiveis.isEmpty()) {
                        return Mono.error(new CodinomeIndisponivelException("Não há codinomes disponíveis na Liga da Justiça"));
                    }

                    return Mono.just(disponiveis.get(new Random().nextInt(disponiveis.size())));
                });
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

    private <T> Mono<T> comResiliencia(Mono<T> mono) {
        return mono
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(e -> {
                    log.error("Falha ao buscar codinomes da API externa: {}", e.getMessage());
                    return Mono.error(new ApiExternaException("Serviço indisponível"));
                });
    }
}
