package com.pitangchallenge.usercars.api.controller;

import com.pitangchallenge.usercars.api.dto.UserResponseDTO;
import com.pitangchallenge.usercars.api.dto.UserUpdateDTO;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserResponseDTO mapUserToResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(), user.getLogin(), user.getPhone(), user.getCars());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapUserToResponse(createdUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users.stream().map(user -> mapUserToResponse(user)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(mapUserToResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO data) {
        User user = new User();

        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setLogin(data.getLogin());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setBirthday(data.getBirthday());

        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(mapUserToResponse(updatedUser));
    }
}