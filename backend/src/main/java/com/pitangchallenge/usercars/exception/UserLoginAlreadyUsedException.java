package com.pitangchallenge.usercars.exception;

public class UserLoginAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserLoginAlreadyUsedException() {
        super("Login already exists");
    }
}
