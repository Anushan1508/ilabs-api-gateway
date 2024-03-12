package com.example.apigateway.domain.dto.addCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartResponse {
    private String responseId;
    private String resultCode;
    private String resultDesc;
}
