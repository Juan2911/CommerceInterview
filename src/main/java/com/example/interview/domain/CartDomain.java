package com.example.interview.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDomain implements Domain {
    @JsonProperty("UserID")
    private String userId;
    @JsonProperty("products")
    private List<CartDetailRequest> products;
    private long cartId;
    private double shipping;
}
