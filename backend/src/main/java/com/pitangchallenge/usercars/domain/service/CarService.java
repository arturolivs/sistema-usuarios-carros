package com.pitangchallenge.usercars.domain.service;

import com.pitangchallenge.usercars.domain.exception.CarLicensePlateAlreadyUsedException;
import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.model.User;
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
        return carRepository.findByUserId(authService.getLoggedUser().getId());
    }

    public Car findById(Long id) {
        return carRepository.findByIdAndUserId(id, authService.getLoggedUser().getId());
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


    public Car update(Long id, Car car) {
        car.setUser(authService.getLoggedUser());
        this.validateLicensePlateExisting(car);

        Car existingCar = this.findById(id);
        updateCarFields(existingCar, car);

        return carRepository.save(existingCar);
    }

    private void updateCarFields(Car existingCar, Car newCar) {
        existingCar.setModel(newCar.getModel());
        existingCar.setYear(newCar.getYear());
        existingCar.setLicensePlate(newCar.getLicensePlate());
        existingCar.setColor(newCar.getColor());
    }

}