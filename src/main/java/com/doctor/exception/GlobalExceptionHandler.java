package com.doctor.exception;

import com.doctor.requestdto.APIResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<APIResponseMessage> handleDoctorNotFoundException(DoctorNotFoundException exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<APIResponseMessage> handleEmptyInputException(EmptyInputException exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<APIResponseMessage> handleInvalidInputException(InvalidInputException exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponseMessage> handleException(Exception exception) {
        APIResponseMessage x = APIResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(x, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
