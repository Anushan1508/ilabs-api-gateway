package com.example.apigateway.domain.service;

import com.example.apigateway.domain.dto.addCard.AddCartRequest;
import com.example.apigateway.domain.dto.addCard.AddCartResponse;
import com.example.apigateway.domain.dto.removeCard.RemoveCartRequest;
import com.example.apigateway.domain.dto.removeCard.RemoveCartResponse;

public interface CartService {
    AddCartResponse addCart(AddCartRequest addCartRequest);

    RemoveCartResponse removeCart(RemoveCartRequest removeCartRequest);
}
