package com.pitangchallenge.usercars.repository;

import com.pitangchallenge.usercars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}