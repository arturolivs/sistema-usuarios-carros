package com.pitangchallenge.usercars.domain.service;

import com.pitangchallenge.usercars.api.dto.SignInDTO;
import com.pitangchallenge.usercars.domain.model.User;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).get();
    }

    public String authenticate(SignInDTO data) {
        var loginPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(loginPassword);
        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);
        user.setLastLogin(new Date());
        userRepository.save(user);

        return token;
    }

    public User getLoggedUser() {
        var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) userDetails;
    }
}