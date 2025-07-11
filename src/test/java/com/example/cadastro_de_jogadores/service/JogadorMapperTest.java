package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.Grupo;
import com.example.cadastro_de_jogadores.model.Jogador;
import com.example.cadastro_de_jogadores.model.dto.JogadorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JogadorMapperTest {

    @Test
    void testToDTO_ComGrupo(){
        Grupo grupo = new Grupo();
        grupo.setNome("Liga da Justiça");

        Jogador jogador = new Jogador();
        jogador.setNome("Bruce Wayne");
        jogador.setGrupo(grupo);

        JogadorResponse jogadorResponse = JogadorMapper.toDTO(jogador);

        assertEquals("Liga da Justiça", jogadorResponse.nomeGrupo());
    }

    @Test
    void testToDTO_SemGrupo() {
        Jogador jogador = new Jogador();
        jogador.setNome("Bruce Wayne");
        jogador.setGrupo(null); // Sem grupo

        JogadorResponse jogadorResponse = JogadorMapper.toDTO(jogador);

        assertEquals("Sem grupo", jogadorResponse.nomeGrupo());
    }

}