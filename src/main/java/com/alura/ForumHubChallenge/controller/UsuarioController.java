package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.usuario.DadosUsuario;
import com.alura.ForumHubChallenge.domain.usuario.DadosUsuarioId;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.alura.ForumHubChallenge.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um encoder Bcrypt
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DadosUsuario dados, UriComponentsBuilder uriBuilder) {

        var novoUsuario = new Usuario(dados);

        // Codifica a senha para gravar no banco de dados
        String senhaCodificada = encoder.encode(dados.senha());
        novoUsuario.setSenha(senhaCodificada);
        usuarioRepository.save(novoUsuario);

       // novoUsuario.setSenha(dados.senha());
       // var uri = uriBuilder.buildAndExpand(novoUsuario.getId()).toUri();
       // return ResponseEntity.created(uri).body(novoUsuario);

        DadosUsuarioId dadosUsuario = new DadosUsuarioId(
                novoUsuario.getId(), novoUsuario.getNome(),
                novoUsuario.getEmail());
        return ResponseEntity.ok(dadosUsuario);
    }

    @GetMapping
    public ResponseEntity<List<DadosUsuarioId>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream()
                .map(usuario -> new DadosUsuarioId(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioId = usuarioRepository.findById(id);
        if (usuarioId.isPresent()) {
            DadosUsuarioId dadosUsuario = new DadosUsuarioId(
                    usuarioId.get().getId(), usuarioId.get().getNome(),
                    usuarioId.get().getEmail());
            return ResponseEntity.ok(dadosUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID do usuário não encontrado");
        }
    }
}