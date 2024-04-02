package com.pitangchallenge.usercars.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pitangchallenge.usercars.domain.exception.CustomTokenExpiredException;
import com.pitangchallenge.usercars.domain.exception.InvalidTokenException;
import com.pitangchallenge.usercars.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("cars-users-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public Optional<String> validateToken(String token) {
        if (token.isEmpty())
            return Optional.empty();

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return Optional.of(
                    JWT.require(algorithm)
                            .withIssuer("cars-users-api")
                            .build()
                            .verify(token)
                            .getSubject());
        } catch (TokenExpiredException ex) {
            throw new CustomTokenExpiredException();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException();
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}