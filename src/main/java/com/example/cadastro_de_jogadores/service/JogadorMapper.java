package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.Grupo;
import com.example.cadastro_de_jogadores.model.Jogador;
import com.example.cadastro_de_jogadores.model.dto.JogadorResponse;

public class JogadorMapper {

    // converte Entidade -> DTO (para leitura)
    public static JogadorResponse toDTO(Jogador jogador) {
        return new JogadorResponse(
                jogador.getId(),
                jogador.getNome(),
                jogador.getEmail(),
                jogador.getTelefone(),
                jogador.getCodinome(),
                extrairNomeGrupo(jogador.getGrupo()),
                jogador.getCreatedAt(),
                jogador.isAtivo()
        );
    }

    private static String extrairNomeGrupo(Grupo grupo) {
        return (grupo != null) ? grupo.getNome() : "Sem grupo";
    }
}
