package com.pitangchallenge.usercars.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.io.PrintWriter;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private final Object responseBody;

    public UnauthorizedEntryPoint(Object responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public final void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(responseBody.toString());
        }
    }
}
