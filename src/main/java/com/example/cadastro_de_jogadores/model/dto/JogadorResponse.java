package com.example.cadastro_de_jogadores.model.dto;

import java.time.LocalDateTime;

public record JogadorResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String codinome,
        String nomeGrupo,
        LocalDateTime createdAt,
        Boolean ativo
) {}
