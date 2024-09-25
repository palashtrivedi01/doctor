package com.doctor.handler;

import com.doctor.dto.ApiResponseMessage;
import com.doctor.exception.ControllerException;
import com.doctor.exception.EmptyInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ApiResponseMessage> message (ControllerException e) {
        ApiResponseMessage apiResoponseException = ApiResponseMessage.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResoponseException, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(EmptyInputException.class)
    public  ResponseEntity<ApiResponseMessage> message (EmptyInputException e){
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return  new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
    }
}