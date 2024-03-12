package com.example.apigateway.domain.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String requestId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
