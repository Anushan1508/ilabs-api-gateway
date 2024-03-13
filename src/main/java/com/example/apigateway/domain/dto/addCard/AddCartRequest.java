package com.example.apigateway.domain.dto.addCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartRequest {
    private String requestId;
    private String item;
    private String token;

}
