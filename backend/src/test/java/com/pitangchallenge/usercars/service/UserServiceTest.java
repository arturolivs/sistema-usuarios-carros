package com.pitangchallenge.usercars.service;

import com.pitangchallenge.usercars.domain.exception.DuplicatedCarLicensePlateException;
import com.pitangchallenge.usercars.domain.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserLoginAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserNotFoundException;
import com.pitangchallenge.usercars.domain.model.Car;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.repository.CarRepository;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import com.pitangchallenge.usercars.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void create_shouldSaveUserWithValidData() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Usuário");
        user.setLastName("para teste");
        user.setEmail("usuario.teste@example.com");
        user.setBirthday(new Date());
        user.setLogin("usuario-login");
        user.setPassword("usuario-senha");
        user.setPhone("11 1111 11111");
        Car car = new Car();
        car.setYear(2024);
        car.setColor("Vermelho");
        car.setModel("Modelo 1");
        car.setLicensePlate("ABC123");
        user.setCars(List.of(car));

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.create(user);

        assertNotNull(savedUser.getId());
        assertEquals("Usuário", savedUser.getFirstName());
        assertTrue(savedUser.getPassword().startsWith("$2a$"));

        Mockito.verify(userRepository).findByEmail(user.getEmail());
        Mockito.verify(userRepository).findByLogin(user.getLogin());
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void create_shouldThrowExceptionForExistingEmail() {
        User user = new User();
        user.setEmail("usuario.teste@example.com");

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(UserEmailAlreadyUsedException.class, () -> userService.create(user));
    }

    @Test()
    public void create_shouldThrowExceptionForExistingLogin() {
        User user = new User();
        user.setLogin("usuario.teste");

        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(new User()));
        assertThrows(UserLoginAlreadyUsedException.class, () -> userService.create(user));
    }

    @Test()
    public void create_shouldThrowExceptionForDuplicatedCarLicensePlate() {
        User user = new User();

        Car car1 = new Car();
        car1.setYear(2024);
        car1.setColor("Vermelho");
        car1.setModel("Modelo 1");
        car1.setLicensePlate("ABC123");

        Car car2 = new Car();
        car2.setYear(2023);
        car2.setColor("Azul");
        car2.setModel("Modelo 2");
        car2.setLicensePlate("ABC123");

        user.setCars(List.of(car1, car2));

        assertThrows(DuplicatedCarLicensePlateException.class, () -> userService.create(user));
    }

    @Test
    public void findAll_shouldReturnAllUsers() {
        List<User> expectedUsers = List.of(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findAll();

        assertEquals(expectedUsers, actualUsers);
        Mockito.verify(userRepository).findAll();
    }

    @Test
    public void findById_shouldReturnUserForExistingId() {
        Long userId = 1L;
        User user = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(userId);

        assertEquals(user, foundUser);
        Mockito.verify(userRepository).findById(userId);
    }

    @Test
    public void findById_shouldThrowExceptionForNonExistingId() {
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(userId));
    }

    @Test
    public void delete_shouldDeleteUser() {
        Long userId = 1L;
        User user = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.delete(userId);

        Mockito.verify(userRepository).delete(user);
    }

    @Test
    public void update_shouldUpdateUserWithValidData() {
        Long userId = 1L;

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("Usuário");
        existingUser.setLastName("para teste");
        existingUser.setEmail("usuario.teste@example.com");
        existingUser.setBirthday(new Date());
        existingUser.setLogin("usuario-login");
        existingUser.setPassword("usuario-senha");
        existingUser.setPhone("11 1111 11111");
        Car existingCar = new Car();
        existingCar.setYear(2024);
        existingCar.setColor("Vermelho");
        existingCar.setModel("Modelo 1");
        existingCar.setLicensePlate("ABC123");
        existingUser.setCars(List.of(existingCar));

        User userWithNewValues = new User();
        userWithNewValues.setId(userId);
        userWithNewValues.setFirstName("Usuário novo");
        userWithNewValues.setLastName("para teste novo");
        userWithNewValues.setEmail("usuario.teste.novo@example.com");
        userWithNewValues.setBirthday(new Date());
        userWithNewValues.setLogin("usuario-login.novo");
        userWithNewValues.setPassword("usuario-senha.novo");
        userWithNewValues.setPhone("22 2222 22222");
        Car carWithNewValues = new Car();
        carWithNewValues.setYear(2023);
        carWithNewValues.setColor("Azul");
        carWithNewValues.setModel("Modelo 1 novo");
        carWithNewValues.setLicensePlate("ABCD1234");
        userWithNewValues.setCars(List.of(carWithNewValues));

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenReturn(userWithNewValues);

        userService.update(userId, userWithNewValues);

        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(userRepository).save(existingUser);
    }

    @Test
    public void update_shouldThrowExceptionForExistingEmail() {
        User user = new User();
        user.setEmail("usuario.teste@example.com");

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(UserEmailAlreadyUsedException.class, () -> userService.update(1L, user));
    }

    @Test()
    public void update_shouldThrowExceptionForExistingLogin() {
        User user = new User();
        user.setLogin("usuario.teste");

        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(new User()));
        assertThrows(UserLoginAlreadyUsedException.class, () -> userService.update(1L, user));
    }
}
