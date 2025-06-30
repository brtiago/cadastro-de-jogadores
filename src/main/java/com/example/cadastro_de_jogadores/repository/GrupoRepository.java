package com.example.cadastro_de_jogadores.repository;

import com.example.cadastro_de_jogadores.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
}
