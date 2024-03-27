package com.pitangchallenge.usercars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Date birthday;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @NotEmpty
    private List<Car> cars = new ArrayList<>();
}