package com.alura.ForumHubChallenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRegistroTopico(
        @NotBlank String titulo,

        @NotBlank String mensagem,

        @NotBlank LocalDateTime data,

        @NotNull Status status,

        // @NotNull DadosUsuario autor,

        // @NotNull @Valid DadosCurso curso

        @NotNull Long autorId,

        @NotNull Long cursoId) {

//    public Long getAutorId() {
//        return autorId;
//    }
//
//    public Long getCursoId() {
//        return cursoId;
//    }
}
