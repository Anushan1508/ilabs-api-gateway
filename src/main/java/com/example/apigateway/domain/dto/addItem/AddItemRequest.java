package com.example.apigateway.domain.dto.addItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemRequest {
    private String requestId;
    private String item_name;
    private String token;
}
