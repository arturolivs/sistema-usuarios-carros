package com.pitangchallenge.usercars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitangchallenge.usercars.api.exceptionhandler.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UnauthorizedEntryPoint() {

    }

    @Override
    public final void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Unauthorized")
                .errorCode(httpStatus.value())
                .build();

        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(errorResponse));
        }
    }
}
