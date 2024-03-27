package com.pitangchallenge.usercars.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int errorCode;
}
