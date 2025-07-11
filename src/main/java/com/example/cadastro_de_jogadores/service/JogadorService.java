package com.example.cadastro_de_jogadores.service;

import com.example.cadastro_de_jogadores.model.Grupo;
import com.example.cadastro_de_jogadores.model.Jogador;
import com.example.cadastro_de_jogadores.model.TipoGrupo;
import com.example.cadastro_de_jogadores.model.dto.JogadorRequest;
import com.example.cadastro_de_jogadores.model.dto.JogadorResponse;
import com.example.cadastro_de_jogadores.repository.GrupoRepository;
import com.example.cadastro_de_jogadores.repository.JogadorRepository;
import com.example.cadastro_de_jogadores.exception.EmailJaExisteException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    public List<JogadorResponse> listarTodos(){
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorMapper::toDTO)
                .toList();
    }

    public JogadorResponse criar(@Valid JogadorRequest request) {
        validarEmailUnico(request.email());

        Grupo grupo = buscarOuCriarGrupo(request.tipoGrupo());

        String codinome = codinomeService.obterCodinomeDisponivel(request.tipoGrupo())
                .block();
        Jogador jogador = Jogador.builder()
                .nome(request.nome())
                .email(request.email())
                .telefone(request.telefone())
                .codinome(codinome)
                .grupo(grupo)
                .build();

        return JogadorMapper.toDTO(jogadorRepository.save(jogador));
    }

    private Grupo buscarOuCriarGrupo(@NotNull(message = "Grupo é obrigatório") TipoGrupo tipoGrupo) {
        return grupoRepository.findByNome(tipoGrupo.getNome())
                .orElseGet(() -> criarNovoGrupo(tipoGrupo));
    }

    private Grupo criarNovoGrupo(@NotNull(message = "Grupo é obrigatório") TipoGrupo tipoGrupo) {
        Grupo novoGrupo = new Grupo();
        novoGrupo.setNome(tipoGrupo.getNome());
        return grupoRepository.save(novoGrupo);
    }


    private void validarEmailUnico(String email) {
        if (jogadorRepository.existsByEmailAndAtivoTrue(email)) {
            throw new EmailJaExisteException("Email já está em uso: " + email);
        }
    }
}
