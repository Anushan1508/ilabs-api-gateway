package com.example.apigateway.application.controller;

import com.example.apigateway.domain.dto.addItem.AddItemRequest;
import com.example.apigateway.domain.dto.addItem.AddItemResponse;
import com.example.apigateway.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base-url-context}")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/additem")
    public AddItemResponse addItem(@RequestBody AddItemRequest addItemRequest) {
        return itemService.addItem(addItemRequest);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello CartController";
    }
}
