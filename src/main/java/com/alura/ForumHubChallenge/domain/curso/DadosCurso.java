package com.alura.ForumHubChallenge.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCurso(

        @NotBlank
        String nome,
        @NotBlank
        String categoria) {
}
