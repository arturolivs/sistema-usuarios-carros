package com.pitangchallenge.usercars.api.exceptionhandler;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.pitangchallenge.usercars.domain.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> createResponseError(HttpStatus status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(status.value())
                .message(message).build();

        return ResponseEntity.status(status)
                .body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Object rejectedValue = null;

        for (FieldError fieldError : fieldErrors) {
            rejectedValue = fieldError.getRejectedValue();

            if(fieldError.getRejectedValue() instanceof ArrayList && ((ArrayList) fieldError.getRejectedValue()).isEmpty()) {
                rejectedValue = null;
            }
        }

        String errorMessage = rejectedValue == null ? "Missing fields" : "Invalid fields";

        return createResponseError(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return createResponseError(HttpStatus.UNAUTHORIZED, "Invalid login or password");
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<Object> handleSignatureVerificationException(SignatureVerificationException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UserEmailAlreadyUsedException.class)
    public ResponseEntity<Object> handleUserEmailAlreadyUsedException(UserEmailAlreadyUsedException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UserLoginAlreadyUsedException.class)
    public ResponseEntity<Object> handleUserLoginAlreadyUsedException(UserLoginAlreadyUsedException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return createResponseError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CarLicensePlateAlreadyUsedException.class)
    public ResponseEntity<Object> handleCarLicensePlateAlreadyUsedException(CarLicensePlateAlreadyUsedException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(DuplicatedCarLicensePlateException.class)
    public ResponseEntity<Object> handleDuplicatedCarLicensePlateException(DuplicatedCarLicensePlateException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
