package com.pitangchallenge.usercars.service;


import com.pitangchallenge.usercars.domain.exception.CarLicensePlateAlreadyUsedException;
import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.repository.CarRepository;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import com.pitangchallenge.usercars.domain.service.AuthService;
import com.pitangchallenge.usercars.domain.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private CarService carService;


    @Test
    public void validateLicensePlateExisting_shouldThrowExceptionForDuplicateLicensePlate() {
        Car car = new Car();
        car.setLicensePlate("ABC123");
        car.setUser(new User());
        Mockito.when(carRepository.findByLicensePlateAndUserId(car.getLicensePlate(), car.getUser().getId())).thenReturn(Optional.of(car));

        assertThrows(CarLicensePlateAlreadyUsedException.class, () -> carService.validateLicensePlateExisting(car));
    }


    @Test
    public void findAllByUserId_shouldReturnAllCarsForUser() {
        User user = new User();
        List<Car> expectedCars = List.of(new Car(), new Car());

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(carRepository.findByUserId(user.getId())).thenReturn(expectedCars);
        Mockito.when(authService.getLoggedUser()).thenReturn(user);

        List<Car> actualCars = carService.findAllByUserId();

        assertEquals(expectedCars, actualCars);
        Mockito.verify(carRepository).findByUserId(user.getId());
    }

    @Test
    public void findById_shouldReturnCarForExistingIdAndUser() {
        Long carId = 1L;
        User user = new User();
        Car expectedCar = new Car();
        expectedCar.setId(carId);
        expectedCar.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(carRepository.findByIdAndUserId(carId, user.getId())).thenReturn(Optional.of(expectedCar));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);

        Car actualCar = carService.findById(carId);

        assertEquals(expectedCar, actualCar);
        Mockito.verify(carRepository).findByIdAndUserId(carId, user.getId());
    }

    @Test
    public void create_shouldCreateCarWithValidLicensePlate() {
        Car car = new Car();
        car.setLicensePlate("ABC123");
        car.setModel("Model X");
        car.setYear(2023);
        car.setColor("Red");
        User user = new User();
        car.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);
        Mockito.when(carRepository.save(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        assertEquals(car.getLicensePlate(), createdCar.getLicensePlate());
        assertEquals(car.getModel(), createdCar.getModel());
        assertEquals(car.getYear(), createdCar.getYear());
        assertEquals(car.getColor(), createdCar.getColor());
        assertEquals(user, createdCar.getUser());
        Mockito.verify(carRepository).save(car);
    }

    @Test
    public void create_shouldThrowExceptionForDuplicatedCarLicensePlate() {
        Car car = new Car();
        car.setLicensePlate("ABC123");
        car.setModel("Model X");
        car.setYear(2023);
        car.setColor("Red");
        User user = new User();
        car.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);
        Mockito.when(carRepository.findByLicensePlateAndUserId(car.getLicensePlate(), car.getUser().getId()))
                .thenReturn(Optional.of(new Car()));

        assertThrows(CarLicensePlateAlreadyUsedException.class, () -> carService.create(car));
    }

    @Test
    public void delete_shouldDeleteCar() {
        Long carId = 1L;
        User user = new User();
        Car car = new Car();
        car.setId(carId);
        car.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);
        Mockito.when(carRepository.findByIdAndUserId(carId, user.getId())).thenReturn(Optional.of(car));

        carService.delete(carId);

        Mockito.verify(carRepository).delete(car);
    }

    @Test
    public void update_shouldUpdateCarWithValidData() {
        Long carId = 1L;

        Car existingCar = new Car();
        existingCar.setId(carId);
        existingCar.setLicensePlate("ABC123");
        existingCar.setModel("Model X");
        existingCar.setYear(2023);
        existingCar.setColor("Vermelho");
        User user = new User();
        existingCar.setUser(user);

        Car carWithNewValues = new Car();
        carWithNewValues.setId(carId);
        carWithNewValues.setLicensePlate("ABC123-novo");
        carWithNewValues.setModel("Model novo");
        carWithNewValues.setYear(2024);
        carWithNewValues.setColor("Azul");
        carWithNewValues.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);
        Mockito.when(carRepository.findByIdAndUserId(carId, user.getId())).thenReturn(Optional.of(existingCar));

        carService.update(carId, carWithNewValues);

        Mockito.verify(carRepository).save(existingCar);

    }


    @Test
    public void update_shouldThrowExceptionForDuplicatedCarLicensePlate() {
        Long carId = 1L;

        Car existingCar = new Car();
        existingCar.setId(carId);
        existingCar.setLicensePlate("ABC123");
        existingCar.setModel("Model X");
        existingCar.setYear(2023);
        existingCar.setColor("Vermelho");
        User user = new User();
        existingCar.setUser(user);

        Car carWithNewValues = new Car();
        carWithNewValues.setId(carId);
        carWithNewValues.setLicensePlate("ABC123");
        carWithNewValues.setModel("Model novo");
        carWithNewValues.setYear(2024);
        carWithNewValues.setColor("Azul");
        carWithNewValues.setUser(user);

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(authService.getLoggedUser()).thenReturn(user);
        Mockito.when(carRepository.findByLicensePlateAndUserId(existingCar.getLicensePlate(), existingCar.getUser().getId()))
                .thenReturn(Optional.of(new Car()));

        assertThrows(CarLicensePlateAlreadyUsedException.class, () -> carService.update(carId, carWithNewValues));

    }

}