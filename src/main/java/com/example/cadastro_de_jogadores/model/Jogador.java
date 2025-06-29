package com.example.cadastro_de_jogadores.model;

import com.example.cadastro_de_jogadores.model.dto.JogadorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank @Size(max = 20)
    private String nome;
    @Email @Column(unique = true)
    @Size(max = 50)
    private String email;

    @NotBlank @Column(unique = true)
    @Size(max = 50)
    private String codinome;
    @Size(max = 50)
    private String telefone;
    @ManyToOne(fetch = FetchType.LAZY)
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return ativo == jogador.ativo && Objects.equals(id, jogador.id) && Objects.equals(nome, jogador.nome) && Objects.equals(email, jogador.email) && Objects.equals(codinome, jogador.codinome) && Objects.equals(telefone, jogador.telefone) && Objects.equals(grupo, jogador.grupo) && Objects.equals(createdAt, jogador.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, codinome, telefone, grupo, createdAt, ativo);
    }
}
