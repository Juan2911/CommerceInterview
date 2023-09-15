package com.example.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyQuantityDomain implements Domain {
    private String productName;
    private long cartId;
    private int quantity;
}
