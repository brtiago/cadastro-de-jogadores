package com.example.cadastro_de_jogadores.service;

import reactor.core.publisher.Mono;

public interface CodinomeStrategy {
    Mono<String> obterCodinomeDisponivel();
}
