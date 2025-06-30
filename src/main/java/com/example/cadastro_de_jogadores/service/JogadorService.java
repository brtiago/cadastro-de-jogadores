package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.dto.JogadorDTO;
import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import com.example.cadastro_de_jogadores.repository.GrupoRepository;
import com.example.cadastro_de_jogadores.repository.JogadorRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;
    private final GrupoRepository grupoRepository;

    public JogadorService(JogadorRepository jogadorRepository,
                          CodinomeService codinomeService,
                          GrupoRepository grupoRepository) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
        this.grupoRepository = grupoRepository;
    }

    public List<JogadorDTO> listarTodos(){
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorMapper::toDTO)
                .toList();
    }


    // TODO: fazer método criar
    public JogadorDTO criar(@Valid JogadorRequest request) {
        return null;
    }

    private void validarEmailUnico(String email) {
        if (jogadorRepository.existsByEmailAndAtivoTrue(email)) {
            throw new EmailJaExisteException("Email já está em uso: " + email);
        }
    }
}
