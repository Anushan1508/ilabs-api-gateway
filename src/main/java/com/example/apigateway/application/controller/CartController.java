package com.example.apigateway.application.controller;

import com.example.apigateway.domain.dto.addItem.AddItemRequest;
import com.example.apigateway.domain.dto.addItem.AddItemResponse;
import com.example.apigateway.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base-url-context}")
public class CartController {
    @Autowired
    CardService cardService;

    @PostMapping("/additem")
    public AddItemResponse addItem(@RequestBody AddItemRequest addItemRequest) {
        System.out.println("addItemRequest From Controller | Cart Service = " + addItemRequest);
        return cardService.addItem(addItemRequest);
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello CartController";
//    }
}
