package com.example.apigateway.domain.service.impl;

import com.example.apigateway.domain.dto.addCard.AddCartRequest;
import com.example.apigateway.domain.dto.addCard.AddCartResponse;
import com.example.apigateway.domain.dto.auth.AuthRequest;
import com.example.apigateway.domain.dto.auth.AuthResponse;
import com.example.apigateway.domain.dto.removeCard.RemoveCartRequest;
import com.example.apigateway.domain.dto.removeCard.RemoveCartResponse;
import com.example.apigateway.domain.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    private final RestTemplate sslRestTemplate;
    @Value("${addCard.Url}")
    private String addCartRestUrl;
    @Value("${removeCard.Url}")
    private String removeCartRestUrl;
    @Value("${authUser.Url}")
    private String authUserUrl;

    public CartServiceImpl(RestTemplate sslRestTemplate, @Value("${addCard.Url}") String addCartRestUrl, @Value("${removeCard.Url}") String removeCartRestUrl, @Value("${authUser.Url}") String authUserUrl) {
        this.sslRestTemplate = sslRestTemplate;
        this.addCartRestUrl = addCartRestUrl;
        this.removeCartRestUrl = removeCartRestUrl;
        this.authUserUrl = authUserUrl;
    }

    @Override
    public AddCartResponse addCart(AddCartRequest addCartRequest) {
        AddCartResponse addCartResponse = new AddCartResponse();
        addCartResponse.setResponseId(addCartRequest.getRequestId());
        AuthRequest authRequest = new AuthRequest(
                addCartRequest.getRequestId(),
                addCartRequest.getToken()
        );
        HttpEntity<AuthRequest> authRequestHttpEntity = new HttpEntity<>(authRequest);
        HttpEntity<AddCartRequest> addCartRequestHttpEntity = new HttpEntity<>(addCartRequest);
        try {
            ResponseEntity<AuthResponse> authResponseResponseEntity = this.sslRestTemplate
                    .exchange(
                            authUserUrl,
                            HttpMethod.POST,
                            authRequestHttpEntity,
                            AuthResponse.class
                    );
            if (Objects.nonNull(authResponseResponseEntity.getBody()) &&
                    authResponseResponseEntity.getBody().getResultCode().equals("200")) {
                ResponseEntity<AddCartResponse> addCartResponseResponseEntity = this.sslRestTemplate
                        .exchange(
                                addCartRestUrl,
                                HttpMethod.POST,
                                addCartRequestHttpEntity,
                                AddCartResponse.class
                        );
                if (Objects.nonNull(addCartResponseResponseEntity.getBody()) && addCartResponseResponseEntity.getBody().getResultCode().equals("200")) {
                    addCartResponse.setResultCode("200");
                    addCartResponse.setResultDesc("Added to Cart Successfully");
                } else {
                    addCartResponse.setResultCode("400");
                    addCartResponse.setResultDesc("Failed to add to Cart");
                }
            } else {
                addCartResponse.setResultCode("401");
                addCartResponse.setResultDesc("Unauthorized User");
                return addCartResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCartResponse;
    }

    @Override
    public RemoveCartResponse removeCart(RemoveCartRequest removeCartRequest) {
        RemoveCartResponse removeCartResponse = new RemoveCartResponse();
        removeCartResponse.setResponseId(removeCartRequest.getRequestId());
        AuthRequest authRequest = new AuthRequest(
                removeCartRequest.getRequestId(),
                removeCartRequest.getToken()
        );
        HttpEntity<AuthRequest> authRequestHttpEntity = new HttpEntity<>(authRequest);
        HttpEntity<RemoveCartRequest> removeCartRequestHttpEntity = new HttpEntity<>(removeCartRequest);
        try {
            ResponseEntity<AuthResponse> authResponseResponseEntity = this.sslRestTemplate
                    .exchange(
                            authUserUrl,
                            HttpMethod.POST,
                            authRequestHttpEntity,
                            AuthResponse.class
                    );
            if (Objects.nonNull(authResponseResponseEntity.getBody()) &&
                    authResponseResponseEntity.getBody().getResultCode().equals("200")) {
                ResponseEntity<RemoveCartResponse> removeCartResponseResponseEntity = this.sslRestTemplate
                        .exchange(
                                removeCartRestUrl,
                                HttpMethod.POST,
                                removeCartRequestHttpEntity,
                                RemoveCartResponse.class
                        );
                if (Objects.nonNull(removeCartResponseResponseEntity.getBody()) && removeCartResponseResponseEntity.getBody().getResultCode().equals("200")) {
                    removeCartResponse.setResultCode("200");
                    removeCartResponse.setResultDesc("Removed to Cart Successfully");
                } else {
                    removeCartResponse.setResultCode("400");
                    removeCartResponse.setResultDesc("Failed to remove to Cart");
                }
            } else {
                removeCartResponse.setResultCode("401");
                removeCartResponse.setResultDesc("Unauthorized User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removeCartResponse;
    }
}
