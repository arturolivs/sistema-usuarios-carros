package com.pitangchallenge.usercars.domain.repository;

import com.pitangchallenge.usercars.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);

    Car findByIdAndUserId(Long id, Long userId);

    Car findByLicensePlateAndUserId(String licensePlate, Long userId);
}