package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.curso.Curso;
import com.alura.ForumHubChallenge.domain.curso.CursoRepository;
import com.alura.ForumHubChallenge.domain.topico.*;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.alura.ForumHubChallenge.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

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
    public List<Topico> listarTopicos () {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Topico> mostrarTopicoPorId (@PathVariable Long id) {
        return topicoRepository.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> registrarTopico(@RequestBody DadosRegistroTopico dados, UriComponentsBuilder uriBuilder) {

        // Find the user and course by their IDs
        Usuario autor = usuarioRepository.findById(dados.autorId()) //getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Curso curso = cursoRepository.findById(dados.cursoId())   //getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        var topico = new Topico(dados);

//        topico.setTitulo(dados.titulo());
//        topico.setMensagem(dados.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);

        // Save the new Topico to the database
        topicoRepository.save(topico);




//        System.out.println(topico);
//        System.out.println(dados);
//
//        var detalhes = new DadosDetalhamentoTopico(topico);
//        System.out.println(detalhes);
//
//        var banco = new DadosBancoTopico(topico);
//        System.out.println(banco);

       // topicoRepository.save(topico);

        //repository.save(topico);
        //return "registrar topico";

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @PutMapping
    public String atualizarTopico() {
        return "atualizar topico";
    }

    @DeleteMapping
    public String deletarTopico() {
        return "topicos deletado";
    }

}
