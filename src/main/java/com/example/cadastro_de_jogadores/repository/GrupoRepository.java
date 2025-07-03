package com.example.cadastro_de_jogadores.repository;

import com.example.cadastro_de_jogadores.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    Optional<Grupo> findByNome(String nome);
}
