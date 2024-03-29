package com.pitangchallenge.usercars.api.controller;

import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllByUserId() {
        List<Car> cars = carService.findAllByUserId();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody Car car) {
        Car createdCar = carService.create(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

}