package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.usuario.DadosUsuario;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.alura.ForumHubChallenge.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DadosUsuario dados, UriComponentsBuilder uriBuilder) {

        var novoUsuario = new Usuario(dados);
       // System.out.println(dados);
        usuarioRepository.save(novoUsuario);

        var uri = uriBuilder.buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }
    @GetMapping
    public List<Usuario> listarUsuarios () {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> mostrarUsuarioPorId (@PathVariable Long id) {

        return usuarioRepository.findById(id);
    }


}