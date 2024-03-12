package com.example.apigateway.application.controller;

import com.example.apigateway.domain.dto.addCard.AddCartRequest;
import com.example.apigateway.domain.dto.addCard.AddCartResponse;
import com.example.apigateway.domain.dto.removeCard.RemoveCartRequest;
import com.example.apigateway.domain.dto.removeCard.RemoveCartResponse;
import com.example.apigateway.domain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${base-url-context}")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/addCart")
    public AddCartResponse addCart(@RequestBody AddCartRequest addCartRequest) {
        return cartService.addCart(addCartRequest);
    }

    @PostMapping("/removeCart")
    public RemoveCartResponse removeCart(@RequestBody RemoveCartRequest removeCartRequest) {
        return cartService.removeCart(removeCartRequest);
    }

}
