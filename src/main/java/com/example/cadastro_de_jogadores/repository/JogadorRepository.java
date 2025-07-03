package com.example.cadastro_de_jogadores.repository;

import com.example.cadastro_de_jogadores.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    boolean existsByEmailAndAtivoTrue(String email);

    Set<String> findCodinomeByGrupoNome(String ligaDaJustiça);
}
