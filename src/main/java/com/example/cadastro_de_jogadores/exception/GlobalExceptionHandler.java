package com.example.cadastro_de_jogadores.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmailJaExisteException.class)
  public ResponseEntity<ErrorResponse> handleEmailJaExiste(
          EmailJaExisteException ex, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
            "EMAIL_JA_EXISTE",
            ex.getMessage(),
            request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(CodinomeIndisponivelException.class)
  public ResponseEntity<ErrorResponse> handleCodinomeIndisponivel(
          CodinomeIndisponivelException ex, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
            "CODINOME_INDISPONIVEL",
            ex.getMessage(),
            request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
  }

  @ExceptionHandler(ApiExternaException.class)
  public ResponseEntity<ErrorResponse> handleApiExterna(
          ApiExternaException ex, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
            "API_EXTERNA_INDISPONIVEL",
            "Serviço temporariamente indisponível. Tente novamente mais tarde.",
            request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
  }
}