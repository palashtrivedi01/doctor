package com.doctor.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResoponseException {
    private String message;
    private HttpStatus httpStatus;
}
