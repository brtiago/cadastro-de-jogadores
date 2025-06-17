package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.Grupo;
import com.example.cadastro_de_jogadores.model.Jogador;
import com.example.cadastro_de_jogadores.model.JogadorDTO;

public class JogadorMapper {

    // converte Entidade -> DTO (para leitura)
    public static JogadorDTO toDTO(Jogador jogador) {
        return new JogadorDTO(
                jogador.getId(),
                jogador.getNome(),
                jogador.getEmail(),
                jogador.getTelefone(),
                jogador.getCodinome(),
                jogador.isAtivo(),
                extrairNomeGrupo(jogador.getGrupo())
        );
    }

    public static Jogador toEntity(JogadorDTO jogadorDTO) {
        Jogador jogador = new Jogador();
        jogador.setId(jogadorDTO.id());
        jogador.setNome(jogadorDTO.nome());
        jogador.setEmail(jogadorDTO.email());
        jogador.setTelefone(jogadorDTO.telefone());
        jogador.setCodinome(jogadorDTO.codinome());
        jogador.setAtivo(jogadorDTO.ativo());

        return jogador;
    }

    private static String extrairNomeGrupo(Grupo grupo) {
        return (grupo != null) ? grupo.getNome() : "Sem grupo";
    }
}
