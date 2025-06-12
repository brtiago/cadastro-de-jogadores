package com.example.cadastro_de_jogadores.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VingadorResponse(
        @JsonProperty("codinome") List<String> codinomes) {

    public List<String> codinomes() {
        return codinomes;
    }
}
