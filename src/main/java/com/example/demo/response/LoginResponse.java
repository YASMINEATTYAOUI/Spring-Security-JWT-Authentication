package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private String token;
    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
