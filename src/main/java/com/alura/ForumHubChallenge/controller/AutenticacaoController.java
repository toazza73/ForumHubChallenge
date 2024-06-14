package com.alura.ForumHubChallenge.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

        @PostMapping
        public String autenticaUsuario() {
            return "autenticado";
        }
    }
