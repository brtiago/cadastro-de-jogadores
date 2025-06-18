package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.JogadorDTO;
import com.example.cadastro_de_jogadores.repository.JogadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;

    public JogadorService(JogadorRepository jogadorRepository, CodinomeService codinomeService) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }

    public List<JogadorDTO> listarTodos(){
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorMapper::toDTO)
                .toList();
    }



}
