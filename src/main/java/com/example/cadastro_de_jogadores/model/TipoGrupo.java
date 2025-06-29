package com.example.cadastro_de_jogadores.model;

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
}