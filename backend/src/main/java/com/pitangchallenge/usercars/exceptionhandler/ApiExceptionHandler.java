package com.pitangchallenge.usercars.exceptionhandler;

import com.pitangchallenge.usercars.exception.ErrorResponse;
import com.pitangchallenge.usercars.exception.UserEmailAlreadyUsedException;
import com.pitangchallenge.usercars.exception.UserLoginAlreadyUsedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(errorMessage);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

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
