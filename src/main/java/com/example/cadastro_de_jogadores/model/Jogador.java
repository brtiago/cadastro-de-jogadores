package com.example.cadastro_de_jogadores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 20)
    private String nome;
    @Email
    @Column(unique = true)
    @Size(max = 50)
    private String email;

    @NotBlank
    @Column(unique = true)
    @Size(max = 50)
    private String codinome;
    @Size(max = 50)
    private String telefone;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Grupo grupo;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean ativo = true;


    public Jogador(JogadorDTO jogadorDTO) {
        this.id = jogadorDTO.id();
        this.nome = jogadorDTO.nome();
        this.email = jogadorDTO.email();
        this.codinome = jogadorDTO.codinome();
        this.telefone = jogadorDTO.telefone();
        this.ativo = jogadorDTO.ativo();
    }

    public Jogador() {

    }

}
