package com.pitangchallenge.usercars.api.dto;


import com.pitangchallenge.usercars.domain.model.Car;

import java.util.Date;
import java.util.List;

public record UserResponseDTO(
            Long id,
            String firstName,
            String lastName,
            String email,
            Date birthday,
            String login,
            String phone,
            List<Car> cars) {
    }
