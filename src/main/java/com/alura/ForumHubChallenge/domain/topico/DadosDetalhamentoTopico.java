package com.alura.ForumHubChallenge.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data) {

    public DadosDetalhamentoTopico(Topico topico){

        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData());
    }
}
