package com.example.apigateway.domain.service;

import com.example.apigateway.domain.dto.addItem.AddItemRequest;
import com.example.apigateway.domain.dto.addItem.AddItemResponse;

public interface CardService {
    AddItemResponse addItem(AddItemRequest addItemRequest);
}
