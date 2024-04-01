package com.pitangchallenge.usercars.domain.service;

import com.pitangchallenge.usercars.domain.exception.DuplicatedCarLicensePlateException;
import com.pitangchallenge.usercars.domain.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserLoginAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserNotFoundException;
import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.repository.CarRepository;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    private void validateEmailAndLoginExisting(User user) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) throw new UserEmailAlreadyUsedException();
        
        Optional<UserDetails> existingUserByLogin = userRepository.findByLogin(user.getLogin());
        if (existingUserByLogin.isPresent()) throw new UserLoginAlreadyUsedException();
    }


    private void checkDuplicateCarLicensePlate(List<Car> cars) {

        boolean hasDuplicate = cars.stream()
                .collect(Collectors.groupingBy(Car::getLicensePlate, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);

        if(hasDuplicate) throw new DuplicatedCarLicensePlateException();
    }

    private void encodePassword(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Transactional
    public User create(User user) {
        this.validateEmailAndLoginExisting(user);
        this.checkDuplicateCarLicensePlate(user.getCars());
        this.encodePassword(user);
        user.setCreatedAt(new Date());
        user.getCars().stream().forEach(car -> car.setUser(user));

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    @Transactional
    public User update(Long id, User user) {
        this.validateEmailAndLoginExisting(user);

        User existingUser = this.findById(id);
        updateUserFields(existingUser, user);
        this.encodePassword(existingUser);

        var saved = userRepository.save(existingUser);

        updateUsersCars(saved, user.getCars());

        return saved;
    }

    private void updateUsersCars(User userSaved, List<Car> newCars) {
        List<Car> carsToRemove = userSaved.getCars().stream()
                .filter(car -> !newCars.contains(car))
                .collect(Collectors.toList());
        carRepository.deleteAll(carsToRemove);

        for (Car newCar : newCars) {
            if (newCar.getId() != null) {
                Car existingCar = carRepository.findById(newCar.getId()).orElseThrow(() -> new EntityNotFoundException("Car not found"));
                existingCar.setYear(newCar.getYear());
                existingCar.setLicensePlate(newCar.getLicensePlate());
                existingCar.setModel(newCar.getModel());
                existingCar.setColor(newCar.getColor());
                existingCar.setUser(userSaved);
                carRepository.save(existingCar);
            } else {
                newCar.setUser(userSaved);
                carRepository.save(newCar);
            }
        }
    }


    private void updateUserFields(User existingUser, User newUser) {
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setBirthday(newUser.getBirthday());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setLogin(newUser.getLogin());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setCars(newUser.getCars());
        existingUser.setPhone(newUser.getPhone());
    }
}