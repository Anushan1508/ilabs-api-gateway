package com.example.apigateway.domain.service.impl;

import com.example.apigateway.domain.dto.addItem.AddItemRequest;
import com.example.apigateway.domain.dto.addItem.AddItemResponse;
import com.example.apigateway.domain.dto.addItem.AuthResponse;
import com.example.apigateway.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {
    private final RestTemplate sslRestTemplate;
    @Value("${addItem.Url}")
    private String addItemUrl;
    @Value("${auth.Url}")
    private String authUrl;

    @Autowired
    public CardServiceImpl(RestTemplate sslRestTemplate, @Value("${addItem.Url}") String addItemUrl, @Value("${auth.Url}") String authUrl){
        this.sslRestTemplate = sslRestTemplate;
        this.addItemUrl = addItemUrl;
        this.authUrl = authUrl;
    }

    @Override
    public AddItemResponse addItem(AddItemRequest addItemRequest) {
        HttpEntity<AddItemRequest> addItemRequestHttpEntity = new HttpEntity<>(addItemRequest);
        AddItemResponse addItemResponse = new AddItemResponse();
        try {
            ResponseEntity<AuthResponse> authResponseResponseEntity = this.sslRestTemplate
                    .exchange(
                            authUrl,
                            HttpMethod.POST,
                            addItemRequestHttpEntity,
                            AuthResponse.class
                    );
            if (Objects.nonNull(authResponseResponseEntity.getBody())){
                System.out.println(authResponseResponseEntity.getBody().getResultDesc());
                if (authResponseResponseEntity.getBody().getResultCode().equals("200")){
                    ResponseEntity<AddItemResponse> addItemResponseResponseEntity = this.sslRestTemplate
                            .exchange(
                                    addItemUrl,
                                    HttpMethod.POST,
                                    addItemRequestHttpEntity,
                                    AddItemResponse.class
                            );
                    if (Objects.nonNull(addItemResponseResponseEntity.getBody())){
                        addItemResponse = addItemResponseResponseEntity.getBody();
                    }
                } else {
                    addItemResponse.setResultDesc("Authentication Failed");
                    addItemResponse.setResultCode("01");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addItemResponse.setResponseId(addItemRequest.getRequestId());
        return addItemResponse;
    }
}
