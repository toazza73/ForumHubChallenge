package com.alura.ForumHubChallenge.domain.topico;

import java.time.LocalDateTime;

public record DadosListaTopicos(Long id, String titulo, String mensagem, LocalDateTime data) {

    public DadosListaTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData());
    }
}
