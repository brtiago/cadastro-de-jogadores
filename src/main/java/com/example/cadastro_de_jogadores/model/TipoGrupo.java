package com.example.cadastro_de_jogadores.model;

import java.util.Arrays;

public enum TipoGrupo {
    LIGA_DA_JUSTICA("Liga da Justiça"),
    VINGADORES("Vingadores");

    private final String nome;

    TipoGrupo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static TipoGrupo fromNome(String nome) {
        return Arrays.stream(values())
                .filter(tipo -> tipo.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de grupo inválido: " + nome));
    }
}