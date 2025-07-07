package com.example.cadastro_de_jogadores.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String codigo,
        String mensagem,
        LocalDateTime timestamp,
        String path
) {
    public ErrorResponse(String codigo, String mensagem) {
        this(codigo, mensagem, LocalDateTime.now(), null);
    }

    public ErrorResponse(String codigo, String mensagem, String path) {
        this(codigo, mensagem, LocalDateTime.now(), path);
    }
}