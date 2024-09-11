package com.doctor.payloads;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseMessage {

    private String message;

    private HttpStatus httpStatus;


}
