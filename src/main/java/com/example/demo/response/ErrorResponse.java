package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
public class ErrorResponse {
    HttpStatus httpStatus;
    String message;
    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
