package com.doctor.handler;

import com.doctor.dto.ApiResponseMessage;
import com.doctor.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ApiResponseMessage> nessage (ControllerException e) {
        ApiResponseMessage apiResoponseException = ApiResponseMessage.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResoponseException, HttpStatus.BAD_REQUEST);

    }
}