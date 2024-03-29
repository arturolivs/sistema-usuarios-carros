package com.pitangchallenge.usercars.domain.exception;

public class CarLicensePlateAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CarLicensePlateAlreadyUsedException() {
        super("License plate already exists");
    }
}
