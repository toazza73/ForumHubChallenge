package com.alura.ForumHubChallenge.security;

import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.chaveSecreta}")
    private String chaveSecreta;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(chaveSecreta);
            return JWT.create()
                    .withIssuer("Forum Hub Challenge")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(chaveSecreta);
            return JWT.require(algoritmo)
                    .withIssuer("Forum Hub Challenge")
                    .build()
                    .verify(tokenJWT)   //verifica se o token está válido
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {

        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }

}