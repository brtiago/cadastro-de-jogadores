package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.TipoGrupo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class CodinomeService {

    private final CodinomeStrategyFactory strategyFactory;

    public CodinomeService(CodinomeStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public Mono<String> obterCodinomeDisponivel(TipoGrupo tipoGrupo) {
        CodinomeStrategy strategy = strategyFactory.getStrategy(tipoGrupo);
        return strategy.obterCodinomeDisponivel();
    }
}
