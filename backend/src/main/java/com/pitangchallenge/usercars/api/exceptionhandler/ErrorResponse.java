package com.pitangchallenge.usercars.api.exceptionhandler;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int errorCode;
}
