package com.pitangchallenge.usercars.exceptionhandler;

import com.pitangchallenge.usercars.exception.ErrorResponse;
import com.pitangchallenge.usercars.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.exception.UserLoginAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserEmailAlreadyUsedException.class)
    public ResponseEntity<Object> handleUserEmailAlreadyUsedException(UserEmailAlreadyUsedException e) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(UserLoginAlreadyUsedException.class)
    public ResponseEntity<Object> handleUserLoginAlreadyUsedException(UserLoginAlreadyUsedException e) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
