package com.example.apigateway.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String responseId;
    private String token;
    private String username;
    private String role;
    private String email;
    private String phone;
    private String address;
    private String name;
    private String surname;
}
