package com.alura.ForumHubChallenge.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// para JPA
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor   //lombok
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;

    public Curso(DadosCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
