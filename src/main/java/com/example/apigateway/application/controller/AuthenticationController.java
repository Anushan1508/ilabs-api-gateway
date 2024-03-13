package com.example.apigateway.application.controller;

import com.example.apigateway.domain.dto.login.LoginRequest;
import com.example.apigateway.domain.dto.login.LoginResponse;
import com.example.apigateway.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base-url-context}")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
