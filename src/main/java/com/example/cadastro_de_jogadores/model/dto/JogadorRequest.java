package com.example.cadastro_de_jogadores.model.dto;

import com.example.cadastro_de_jogadores.model.TipoGrupo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogadorRequest (
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email deve ter formato válido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        String telefone,

        @NotNull(message = "Grupo é obrigatório")
        @Enumerated(EnumType.STRING)
        TipoGrupo tipoGrupo
){}
