package com.doctor.dto;

import jakarta.validation.constraints.AssertFalse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseMessage {
    private  String message;
    private HttpStatus httpStatus;


}
