package com.alura.ForumHubChallenge.controller;

import com.alura.ForumHubChallenge.domain.usuario.DadosUsuario;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.alura.ForumHubChallenge.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um encoder Bcrypt
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DadosUsuario dados, UriComponentsBuilder uriBuilder) {

        var novoUsuario = new Usuario(dados);
       // System.out.println(dados);

        // Codifica a senha para gravar no banco de dados
        String senhaCodificada = encoder.encode(dados.senha());
        novoUsuario.setSenha(senhaCodificada);
        usuarioRepository.save(novoUsuario);

        novoUsuario.setSenha(dados.senha());

        var uri = uriBuilder.buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);

        //return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public List<Usuario> listarUsuarios () {

        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> mostrarUsuarioPorId (@PathVariable Long id) {

        return Optional.ofNullable(usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

}