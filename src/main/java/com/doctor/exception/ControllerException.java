package com.doctor.exception;

import org.springframework.http.HttpStatus;

public class ControllerException extends Exception {


    public ControllerException(String message) {
        super(message);
    }
}
