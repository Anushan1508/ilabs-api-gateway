package com.example.apigateway.domain.service;

import com.example.apigateway.domain.dto.login.LoginRequest;
import com.example.apigateway.domain.dto.login.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
