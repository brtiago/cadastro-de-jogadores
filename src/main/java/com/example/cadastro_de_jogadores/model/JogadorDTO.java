package com.example.cadastro_de_jogadores.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record JogadorDTO(

        Integer id,
        @NotBlank(message = "Name cannot be blank")
        String nome,
        @Email(message = "Formato de e-mail inválido")
        @NotBlank
        String email,
        String telefone,
        String codinome,
        Boolean ativo,
        String nomeGrupo
) { }