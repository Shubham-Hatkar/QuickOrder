package com.example.QuickOrder.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Farmer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int farmerId;

    @Column(nullable = false)
    private String name;

    private int age;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phoneNo;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();
}
