package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.TipoGrupo;
import org.springframework.stereotype.Component;

@Component
public class CodinomeStrategyFactory {

    private final VingadoresCodinomeStrategy vingadoresStrategy;
    private final LigaCodinomeStrategy ligaStrategy;

    public CodinomeStrategyFactory(
            VingadoresCodinomeStrategy vingadoresStrategy,
            LigaCodinomeStrategy ligaStrategy
    ) {
        this.vingadoresStrategy = vingadoresStrategy;
        this.ligaStrategy = ligaStrategy;
    }

    public CodinomeStrategy getStrategy(TipoGrupo tipo) {

        return switch (tipo) {
            case VINGADORES -> vingadoresStrategy;
            case LIGA_DA_JUSTICA -> ligaStrategy;
        };
    }


}
