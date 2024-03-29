package com.pitangchallenge.usercars.domain.service;

import com.pitangchallenge.usercars.domain.exception.CarLicensePlateAlreadyUsedException;
import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AuthService authService;

    private void validateLicensePlateExisting(Car car) {
        Optional<Car> existingLicensePlate = Optional.ofNullable(
                carRepository.findByLicensePlateAndUserId(car.getLicensePlate(), car.getUser().getId()));

        if (existingLicensePlate.isPresent()) throw new CarLicensePlateAlreadyUsedException();
    }

    public List<Car> findAllByUserId() {
        return carRepository.findByUserId(authService.getUserId());
    }

    public Car findById(Long id) {
        return carRepository.findByIdAndUserId(id, authService.getUserId());
    }

    public Car create(Car car) {
        car.setUser(authService.getLoggedUser());
        this.validateLicensePlateExisting(car);

        return carRepository.save(car);
    }

    public void delete(Long id) {
        Car car = this.findById(id);
        carRepository.delete(car);
    }

}