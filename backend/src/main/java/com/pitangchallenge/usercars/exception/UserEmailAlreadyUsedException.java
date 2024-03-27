package com.pitangchallenge.usercars.exception;

public class UserEmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserEmailAlreadyUsedException() {
        super("Email already exists");
    }
}
