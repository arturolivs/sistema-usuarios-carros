package com.pitangchallenge.usercars.service;

import com.pitangchallenge.usercars.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.exception.UserLoginAlreadyUsedException;
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

    public User createUser(User user) throws UserEmailAlreadyUsedException, UserLoginAlreadyUsedException {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) throw new UserEmailAlreadyUsedException();

        Optional<User> existingUserByLogin = userRepository.findByLogin(user.getLogin());
        if (existingUserByLogin.isPresent()) throw new UserLoginAlreadyUsedException();

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}