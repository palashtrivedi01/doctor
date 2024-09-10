package com.doctor.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {

    private List<String> imageName;

    private String message;

    private Boolean success;

    private HttpStatus status;

}

