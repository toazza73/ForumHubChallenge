package com.alura.ForumHubChallenge.domain.topico;

import com.alura.ForumHubChallenge.domain.curso.Curso;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosBancoTopico(String titulo, String mensagem, LocalDateTime data, Status status, Usuario autor, Curso curso) {

    public DadosBancoTopico(Topico topico){
        this(topico.getTitulo(),
                topico.getMensagem(),
                topico.getData(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
                );
    }
}

