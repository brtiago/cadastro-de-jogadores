package com.example.cadastro_de_jogadores.service.exception;

public class ApiExternaException extends RuntimeException{
    public ApiExternaException(String message) {
        super(message);
    }
}
