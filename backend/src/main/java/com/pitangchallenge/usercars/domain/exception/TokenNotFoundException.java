package com.pitangchallenge.usercars.domain.exception;

public class TokenNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenNotFoundException() {
        super("Unauthorized");
    }
}
