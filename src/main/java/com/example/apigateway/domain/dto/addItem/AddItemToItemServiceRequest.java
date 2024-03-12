package com.example.apigateway.domain.dto.addItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToItemServiceRequest {
    private String requestId;
    private String itemName;
}
