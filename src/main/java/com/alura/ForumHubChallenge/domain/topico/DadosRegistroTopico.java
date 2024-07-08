package com.alura.ForumHubChallenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRegistroTopico(
        @NotBlank String titulo,

        @NotBlank String mensagem,

        @NotBlank LocalDateTime data,

        @NotNull Status status,

        @NotNull Long autorId,

        @NotNull Long cursoId) {

}
