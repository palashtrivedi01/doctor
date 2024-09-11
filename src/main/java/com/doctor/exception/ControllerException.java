package com.doctor.exception;

public class ControllerException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ControllerException(String message) {
        super(message);
    }
}
