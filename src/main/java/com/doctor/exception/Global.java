package com.doctor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Global {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ApiResoponseException> message (ControllerException e) {
        ApiResoponseException apiResoponseException = ApiResoponseException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResoponseException, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResoponseException> message (BusinessException e) {
        ApiResoponseException apiResoponseException = ApiResoponseException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResoponseException, HttpStatus.BAD_REQUEST);
    }
}
