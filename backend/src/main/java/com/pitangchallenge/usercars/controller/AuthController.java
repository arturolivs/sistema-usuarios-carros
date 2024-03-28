package com.pitangchallenge.usercars.controller;

import com.pitangchallenge.usercars.dto.SignInDTO;
import com.pitangchallenge.usercars.dto.SignInResponseDTO;
import com.pitangchallenge.usercars.model.User;
import com.pitangchallenge.usercars.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/signin")
    public ResponseEntity signIn(@RequestBody @Valid SignInDTO data) {
        var loginPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(loginPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new SignInResponseDTO(token));
    }
}
