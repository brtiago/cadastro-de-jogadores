package com.example.cadastro_de_jogadores.service.exception;

public class EmailJaExisteException extends RuntimeException {
    public EmailJaExisteException(String message) {
        super(message);
    }
}
