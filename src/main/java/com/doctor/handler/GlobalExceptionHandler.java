package com.doctor.handler;

import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.payloads.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

       @ExceptionHandler(BusinessException.class)
       public ResponseEntity<ApiResponseMessage> businessException(BusinessException businessException) {
            ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                    .message(businessException.getMessage())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
       }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponseMessage> noResourceFoundException(NoResourceFoundException noResourceFoundException) {
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message("Null cannot be used to access the data!")
                .httpStatus(HttpStatus.NO_CONTENT)
                .build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("Invalid Input")
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
