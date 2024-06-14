package com.alura.ForumHubChallenge.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// para JPA
@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor   //lombok
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    public Usuario(DadosUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }
}

