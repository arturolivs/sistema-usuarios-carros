package com.pitangchallenge.usercars.domain.exception;

public class CustomTokenExpiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomTokenExpiredException() {
        super("Unauthorized - invalid session");
    }
}
