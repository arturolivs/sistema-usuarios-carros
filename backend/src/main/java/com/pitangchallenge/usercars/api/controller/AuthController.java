package com.pitangchallenge.usercars.api.controller;

import com.pitangchallenge.usercars.api.dto.SignInDTO;
import com.pitangchallenge.usercars.api.dto.SignInResponseDTO;
import com.pitangchallenge.usercars.domain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

@Autowired
private AuthService authService;

    @PostMapping("/api/signin")
    public ResponseEntity signIn(@RequestBody @Valid SignInDTO data) {
        String token = authService.authenticate(data);
        return ResponseEntity.ok(new SignInResponseDTO(token));
    }
}
