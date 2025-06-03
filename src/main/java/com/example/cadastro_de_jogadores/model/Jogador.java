package com.example.cadastro_de_jogadores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Column(unique = true)
    private String email;

    private String codinome;
    private String telefone;
    @ManyToOne
    private Grupo grupo;
    @CreationTimestamp
    private final LocalDate createdAt = LocalDate.now();
    private boolean ativo = true;
}
