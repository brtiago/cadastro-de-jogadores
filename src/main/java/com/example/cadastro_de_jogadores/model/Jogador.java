package com.example.cadastro_de_jogadores.model;

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
    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
    private boolean ativo = true;

    public Jogador() {}

    public static class Builder {
        private String nome;
        private String email;
        private String telefone;
        private String codinome;
        private Grupo grupo;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder codinome(String codinome) {
            this.codinome = codinome;
            return this;
        }

        public Builder grupo(Grupo grupo) {
            this.grupo = grupo;
            return this;
        }

        public Jogador build() {
            Jogador jogador = new Jogador();
            jogador.setNome(this.nome);
            jogador.setEmail(this.email);
            jogador.setTelefone(this.telefone);
            jogador.setCodinome(this.codinome);
            jogador.setGrupo(this.grupo);
            return jogador;
        }
    }

    public static Builder builder() {
        return new Builder();
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(id, jogador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
