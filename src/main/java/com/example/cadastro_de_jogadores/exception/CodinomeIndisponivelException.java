package com.example.cadastro_de_jogadores.exception;

public class CodinomeIndisponivelException extends RuntimeException {
    public CodinomeIndisponivelException(String message) {
        super(message);
    }
}