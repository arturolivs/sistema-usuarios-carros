package com.pitangchallenge.usercars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitangchallenge.usercars.api.exceptionhandler.ErrorResponse;
import com.pitangchallenge.usercars.domain.exception.InvalidTokenException;
import com.pitangchallenge.usercars.domain.repository.UserRepository;
import com.pitangchallenge.usercars.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.util.Optional;

@Component
public class TokenJWTFilter extends GenericFilterBean {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private void throwHttpError(HttpServletResponse httpResponse, ServletResponse response, int status, Exception ex)
            throws IOException {
        httpResponse.setStatus(status);
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(status)
                .build();

        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(errorResponse));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            var token = this.recoverToken(request);

            if (!token.isEmpty()) {
                var login = tokenService.validateToken(token).get();
                Optional<UserDetails> user = userRepository.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
                        user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (InvalidTokenException ex) {
            throwHttpError(httpResponse, response, HttpStatus.BAD_REQUEST.value(), ex);
        } catch (Exception ex) {
            throwHttpError(httpResponse, response, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex);
        }
    }

    private String recoverToken(ServletRequest request) {
        var authHeader = ((HttpServletRequest) request).getHeader("Authorization");
        if (authHeader == null)
            return "";

        return authHeader.replace("Bearer", "").trim();
    }
}