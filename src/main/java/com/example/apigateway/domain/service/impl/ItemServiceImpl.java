package com.example.apigateway.domain.service.impl;

import com.example.apigateway.domain.dto.addItem.AddItemRequest;
import com.example.apigateway.domain.dto.addItem.AddItemResponse;
import com.example.apigateway.domain.dto.addItem.AddItemToItemServiceRequest;
import com.example.apigateway.domain.dto.auth.AuthRequest;
import com.example.apigateway.domain.dto.auth.AuthResponse;
import com.example.apigateway.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ItemServiceImpl implements ItemService {
    private final RestTemplate sslRestTemplate;
    @Value("${addItem.Url}")
    private String addItemUrl;
    @Value("${auth.Url}")
    private String authUrl;

    @Autowired
    public ItemServiceImpl(RestTemplate sslRestTemplate, @Value("${addItem.Url}") String addItemUrl, @Value("${auth.Url}") String authUrl){
        this.sslRestTemplate = sslRestTemplate;
        this.addItemUrl = addItemUrl;
        this.authUrl = authUrl;
    }

    @Override
    public AddItemResponse addItem(AddItemRequest addItemRequest) {
        AddItemResponse addItemResponse = new AddItemResponse();
        AuthRequest authRequest = new AuthRequest(
                addItemRequest.getRequestId(),
                addItemRequest.getToken()
        );
        AddItemToItemServiceRequest addItemToItemServiceRequest = new AddItemToItemServiceRequest(
                addItemRequest.getRequestId(),
                addItemRequest.getItem_name()
        );
        HttpEntity<AuthRequest> authRequestHttpEntity = new HttpEntity<>(authRequest);
        HttpEntity<AddItemToItemServiceRequest> addItemToItemServiceRequestHttpEntity = new HttpEntity<>(addItemToItemServiceRequest);
        try {
            ResponseEntity<AuthResponse> authResponseResponseEntity = this.sslRestTemplate
                    .exchange(
                            authUrl,
                            HttpMethod.POST,
                            authRequestHttpEntity,
                            AuthResponse.class
                    );
            if (Objects.nonNull(authResponseResponseEntity.getBody())){
                if (authResponseResponseEntity.getBody().getResultCode().equals("200")){
                    ResponseEntity<AddItemResponse> addItemResponseResponseEntity = this.sslRestTemplate
                            .exchange(
                                    addItemUrl,
                                    HttpMethod.POST,
                                    addItemToItemServiceRequestHttpEntity,
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
