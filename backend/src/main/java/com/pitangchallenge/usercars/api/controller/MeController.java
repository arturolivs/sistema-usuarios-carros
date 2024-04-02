package com.pitangchallenge.usercars.api.controller;

import com.pitangchallenge.usercars.api.dto.MeDTO;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.service.AuthService;
import com.pitangchallenge.usercars.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MeController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MeDTO> getUserInfo() {
        User user = userService.findById(authService.getLoggedUser().getId());

        return ResponseEntity.ok(new MeDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin(),
                user.getPhone(),
                user.getCars(),
                user.getLastLogin(),
                user.getCreatedAt()
                ));
    }
}
