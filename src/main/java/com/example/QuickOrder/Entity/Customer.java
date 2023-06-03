package com.example.QuickOrder.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;

    private int customerAge;

    private String customerAddress;

    @Column(nullable = false,unique = true)
    private String customerEmail;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    CustomerCart customerCart;

}
