package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.curso.Curso;
import com.alura.ForumHubChallenge.domain.curso.CursoRepository;
import com.alura.ForumHubChallenge.domain.curso.DadosCurso;
import com.alura.ForumHubChallenge.domain.curso.DadosCursoId;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody @Valid DadosCurso dados, UriComponentsBuilder uriBuilder) {
        var novoCurso = new Curso(dados);
        cursoRepository.save(novoCurso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(novoCurso.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCurso);
    }
    @GetMapping
    public List<Curso> listarCursos () {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarCursoPorId(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            DadosCursoId dadosCurso = new DadosCursoId(curso.getId(), curso.getNome(), curso.getCategoria());
            return ResponseEntity.ok(dadosCurso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID do curso não encontrado");
        }
    }
}