package com.doctor.handler;

import com.doctor.exception.ControllerException;
import com.doctor.payloads.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
       public ResponseEntity<ApiResponseMessage> controllerException(ControllerException controllerException) {

           ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                   .message(controllerException.getMessage())
                   .httpStatus(HttpStatus.BAD_REQUEST)
                   .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
       }


}
