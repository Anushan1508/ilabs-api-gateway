package com.example.apigateway.domain.dto.addItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemResponse {
    private String responseId;
    private String resultCode;
    private String resultDesc;
}
