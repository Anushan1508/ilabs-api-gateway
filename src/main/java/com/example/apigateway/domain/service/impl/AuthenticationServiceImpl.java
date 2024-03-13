package com.example.apigateway.domain.service.impl;

import com.example.apigateway.domain.dto.login.LoginRequest;
import com.example.apigateway.domain.dto.login.LoginResponse;
import com.example.apigateway.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RestTemplate sslRestTemplate;
    @Value("${login.url}")
    private String loginUrl;

    @Autowired
    public AuthenticationServiceImpl(RestTemplate sslRestTemplate, @Value("${login.url}") String loginUrl) {
        this.sslRestTemplate = sslRestTemplate;
        this.loginUrl = loginUrl;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        HttpEntity<LoginRequest> loginRequestHttpEntity = new HttpEntity<>(loginRequest);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResponseId(loginRequest.getRequestId());
        try {
            ResponseEntity<LoginResponse> loginResponseResponseEntity = this.sslRestTemplate
                    .exchange(
                            loginUrl,
                            HttpMethod.POST,
                            loginRequestHttpEntity,
                            LoginResponse.class
                    );
            if (Objects.nonNull(loginResponseResponseEntity.getBody()) && loginResponseResponseEntity.getBody().getResultCode().equals("200")) {
                loginResponse.setToken(loginResponseResponseEntity.getBody().getToken());
                loginResponse.setResultCode("200");
                loginResponse.setResultDesc("Login Success");
            } else {
                loginResponse.setResultCode("401");
                loginResponse.setResultDesc("Login Failed");
            }
            return loginResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginResponse;
    }
}
