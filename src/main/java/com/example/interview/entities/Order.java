package com.example.interview.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "order_cart")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements EntityObject, Serializable {
    @Id
    @GeneratedValue
    private long id;
    private int products;
    private double discounts;
    private double shipping;
    private double orderTotal;

}
