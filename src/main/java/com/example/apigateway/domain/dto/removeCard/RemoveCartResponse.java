package com.example.apigateway.domain.dto.removeCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveCartResponse {
    private String responseId;
    private String resultCode;
    private String resultDesc;
}
