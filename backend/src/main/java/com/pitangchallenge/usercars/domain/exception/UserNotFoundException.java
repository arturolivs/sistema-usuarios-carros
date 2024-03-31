package com.pitangchallenge.usercars.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.format("User not found. id: %d", id));
    }
}
