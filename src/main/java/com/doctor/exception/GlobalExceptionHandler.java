package com.doctor.exception;

import com.doctor.requestdto.APIResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<APIResponseMessage> handleDoctorNotFoundException(ControllerException exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIResponseMessage> handleInvalidInputException(BusinessException exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponseMessage> handleException(Exception exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(x, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
