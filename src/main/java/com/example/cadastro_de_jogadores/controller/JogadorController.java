package com.example.cadastro_de_jogadores.controller;

import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import com.example.cadastro_de_jogadores.model.dto.JogadorResponse;
import com.example.cadastro_de_jogadores.service.JogadorService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public ResponseEntity<List<JogadorResponse>> listarTodos() {
        return ResponseEntity.ok(jogadorService.listarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JogadorResponse> listarPorId(@PathVariable Integer id) {
        JogadorResponse jogador = jogadorService.buscarId(id);
        return ResponseEntity.ok(jogador);
    }

    @PostMapping
    public ResponseEntity<JogadorResponse> criar(@Valid @RequestBody JogadorRequest request) {
        JogadorResponse jogador = jogadorService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogador);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<JogadorResponse> atualizar(@PathVariable int id, @Valid @RequestBody JogadorRequest request) {
        JogadorResponse jogador = jogadorService.atualizar(id, request);
        return ResponseEntity.ok(jogador);

    }
}