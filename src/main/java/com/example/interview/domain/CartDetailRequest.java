package com.example.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailRequest implements Domain {
    private String productName;
    private String category;
    private double price;
    private int quantity;
}
