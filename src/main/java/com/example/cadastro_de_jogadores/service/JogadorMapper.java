package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.Grupo;
import com.example.cadastro_de_jogadores.model.Jogador;
import com.example.cadastro_de_jogadores.model.dto.JogadorDTO;
import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;

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


    public static Jogador fromRequest(
            JogadorRequest request, String codinome, Grupo grupo) {
        Jogador jogador = new Jogador();
        jogador.setNome(request.nome());
        jogador.setEmail(request.email());
        jogador.setTelefone(request.telefone());
        jogador.setCodinome(codinome);
        jogador.setGrupo(grupo);

        return jogador;
    }


    private static String extrairNomeGrupo(Grupo grupo) {
        return (grupo != null) ? grupo.getNome() : "Sem grupo";
    }
}
