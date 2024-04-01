package com.pitangchallenge.usercars.api.controller;

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

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO data) {
        User user = new User();

        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setLogin(data.getLogin());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setBirthday(data.getBirthday());

        return ResponseEntity.ok(userService.update(id, user));
    }
}