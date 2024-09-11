package com.doctor.exception;


public class ControllerException extends   RuntimeException {
    public ControllerException(String message) {
        super(message);
    }
}
