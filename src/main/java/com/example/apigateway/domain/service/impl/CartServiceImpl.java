package com.example.apigateway.domain.service.impl;

import com.example.apigateway.domain.dto.addCard.AddCartRequest;
import com.example.apigateway.domain.dto.addCard.AddCartResponse;
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

    public CartServiceImpl(RestTemplate sslRestTemplate, @Value("${addCard.Url}") String addCartRestUrl, @Value("${removeCard.Url}") String removeCartRestUrl) {
        this.sslRestTemplate = sslRestTemplate;
        this.addCartRestUrl = addCartRestUrl;
        this.removeCartRestUrl = removeCartRestUrl;
    }

    @Override
    public AddCartResponse addCart(AddCartRequest addCartRequest) {
        AddCartResponse addCartResponse = new AddCartResponse();
        addCartResponse.setResponseId(addCartRequest.getRequestId());
        HttpEntity<AddCartRequest> addCartRequestHttpEntity = new HttpEntity<>(addCartRequest);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCartResponse;
    }

    @Override
    public RemoveCartResponse removeCart(RemoveCartRequest removeCartRequest) {
        RemoveCartResponse removeCartResponse = new RemoveCartResponse();
        removeCartResponse.setResponseId(removeCartRequest.getRequestId());
        HttpEntity<RemoveCartRequest> removeCartRequestHttpEntity = new HttpEntity<>(removeCartRequest);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removeCartResponse;
    }
}
