package com.example.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalsDomain implements Domain {
    private int products;
    private double discounts;
    private double shipping;
    private double order;
}
