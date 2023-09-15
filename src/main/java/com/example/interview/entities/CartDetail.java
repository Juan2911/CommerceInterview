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
@Table(name = "cart_details")
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail implements Serializable {
    //I add this class in cart to get a detail (row by cart) of the specific cart
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Product product;
    private int quantity;
    private double subTotal;
}
