package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.JogadorDTO;
import com.example.cadastro_de_jogadores.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository){
        this.jogadorRepository = jogadorRepository;
    }

    public List<JogadorDTO> listarTodos(){
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorMapper::toDTO)
                .toList();
    }

}
