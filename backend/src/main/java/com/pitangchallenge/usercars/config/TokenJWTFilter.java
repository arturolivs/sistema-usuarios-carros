package com.pitangchallenge.usercars.config;

import com.pitangchallenge.usercars.domain.repository.UserRepository;
import com.pitangchallenge.usercars.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@Component
public class TokenJWTFilter extends GenericFilterBean {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        var token = this.recoverToken(request);

        if (!token.isEmpty()) {
            var login = tokenService.validateToken(token).get();
            Optional<UserDetails> user = userRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(ServletRequest request) {
        var authHeader = ((HttpServletRequest) request).getHeader("Authorization");
        if (authHeader == null)
            return "";

        return authHeader.replace("Bearer", "").trim();
    }
}