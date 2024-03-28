package com.pitangchallenge.usercars.domain.service;

import com.pitangchallenge.usercars.domain.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserLoginAlreadyUsedException;
import com.pitangchallenge.usercars.domain.exception.UserNotFoundException;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        
        Optional<UserDetails> existingUserByLogin = userRepository.findByLogin(user.getLogin());
        if (existingUserByLogin.isPresent()) throw new UserLoginAlreadyUsedException();
    }

    private void encodePassword(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User createUser(User user) {
        this.validateEmailAndLoginExisting(user);
        this.encodePassword(user);

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
        this.encodePassword(existingUser);

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