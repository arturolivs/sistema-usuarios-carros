package com.pitangchallenge.usercars.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateDTO {

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
}
