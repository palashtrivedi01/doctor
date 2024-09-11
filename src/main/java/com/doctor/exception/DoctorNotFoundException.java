package com.doctor.exception;

public class DoctorNotFoundException extends  Exception{
    public DoctorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
