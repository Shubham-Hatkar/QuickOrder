package com.example.QuickOrder.Entity;

import com.example.QuickOrder.Repository.CustomerRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerCart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    // cart and product relationship ->
    @OneToMany(mappedBy = "customerCart", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    // cart and customer relationship ->
    @OneToOne
    @JoinColumn
    Customer customer;
}
