package com.example.interview.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements EntityObject, Serializable {
    //to store in database always we need an Id;
    @Id
    @GeneratedValue
    private long id;
    private String userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<CartDetail> cartDetails;
}
