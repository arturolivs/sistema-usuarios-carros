package com.pitangchallenge.usercars.api.dto;

import com.pitangchallenge.usercars.domain.model.Car;
import java.util.List;

import java.util.Date;

public record MeDTO(String firstName,
        String lastName,
        String email,
        Date birthday,
        String login,
        String phone,
        List<Car> cars,
        Date lastLogin,
        Date createdAt) {
}
