package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.curso.Curso;
import com.alura.ForumHubChallenge.domain.curso.CursoRepository;
import com.alura.ForumHubChallenge.domain.topico.*;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.alura.ForumHubChallenge.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<DadosListaTopicos> listarTopicos () {
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream().map(topico -> new DadosListaTopicos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarTopicoPorId (@PathVariable Long id) {
        Optional<Topico> topicoId = topicoRepository.findById(id);
        if (topicoId.isPresent()) {
            DadosTopicoId dadosTopico = new DadosTopicoId(
                    topicoId.get().getId(),
                    topicoId.get().getTitulo(),
                    topicoId.get().getMensagem(),
                    topicoId.get().getData(),
                    topicoId.get().getAutor().getNome(),
                    topicoId.get().getStatus()
            );
            return ResponseEntity.ok(dadosTopico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID do tópico não encontrado");
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> registrarTopico(@RequestBody DadosRegistroTopico dados, UriComponentsBuilder uriBuilder) {

        // Find the user and course by their IDs
        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        var topico = new Topico(dados);
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.setTitulo(dados.titulo());
            topico.setMensagem(dados.mensagem());
            topico.setStatus(dados.status());
            topicoRepository.save(topico);
            return ResponseEntity.ok(new DadosTopicoId(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensagem(),
                    topico.getData(),
                    topico.getAutor().getNome(),
                    topico.getStatus()
            ));
        } else {
            return ResponseEntity.badRequest().body("O Tópico " + id + " não existe no Banco de Dados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topicoRepository.delete(topico);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("O Tópico " + id + " não existe no Banco de Dados");
        }
    }
}
