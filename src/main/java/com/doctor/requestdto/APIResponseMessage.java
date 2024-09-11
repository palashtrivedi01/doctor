package com.doctor.requestdto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class APIResponseMessage {
    String message;
    HttpStatus status;

}
