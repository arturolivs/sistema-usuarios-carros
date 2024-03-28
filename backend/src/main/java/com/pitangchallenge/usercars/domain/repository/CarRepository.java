package com.pitangchallenge.usercars.domain.repository;

import com.pitangchallenge.usercars.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}