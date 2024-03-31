package com.pitangchallenge.usercars.config;

import com.pitangchallenge.usercars.domain.repository.UserRepository;
import com.pitangchallenge.usercars.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class TokenJWTFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        List<String> publicRoutes = Arrays.asList("/api/signin", "/api/users");

        if (publicRoutes.stream().anyMatch(route -> request.getRequestURI().startsWith(route))) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);

        var login = tokenService.validateToken(token);
        Optional<UserDetails> user = userRepository.findByLogin(login);

        var authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return "";

        return authHeader.replace("Bearer", "").trim();
    }
}