package com.alura.ForumHubChallenge.domain.topico;

import java.time.LocalDateTime;

public record DadosTopicoId(Long id, String titulo, String mensagem, LocalDateTime data, String nome, Status status) {

}
