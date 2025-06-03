package com.example.cadastro_de_jogadores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
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
    @NotBlank
    @Size(max = 50)
    private String telefone;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Grupo grupo;
    @CreationTimestamp
    private LocalDate createdAt = LocalDate.now();
    private boolean ativo = true;
}
