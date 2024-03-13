package com.example.apigateway.domain.dto.removeCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveCartRequest {
    private String requestId;
    private String item;
    private String token;

}
