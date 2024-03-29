package com.pitangchallenge.usercars.domain.exception;

public class DuplicatedCarLicensePlateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatedCarLicensePlateException() {
        super("Has duplicated license plate");
    }
}
