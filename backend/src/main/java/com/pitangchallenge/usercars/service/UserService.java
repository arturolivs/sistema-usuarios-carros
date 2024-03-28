package com.pitangchallenge.usercars.service;

import com.pitangchallenge.usercars.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.exception.UserLoginAlreadyUsedException;
import com.pitangchallenge.usercars.exception.UserNotFoundException;
import com.pitangchallenge.usercars.model.User;
import com.pitangchallenge.usercars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private void validateEmailAndLoginExisting(User user) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) throw new UserEmailAlreadyUsedException();

        Optional<User> existingUserByLogin = userRepository.findByLogin(user.getLogin());
        if (existingUserByLogin.isPresent()) throw new UserLoginAlreadyUsedException();
    }

    public User createUser(User user) {
        this.validateEmailAndLoginExisting(user);
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

    public User update(Long id, User user) {
        this.validateEmailAndLoginExisting(user);
        User existingUser = this.findById(id);
        updateUserFields(existingUser, user);

        return userRepository.save(existingUser);
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