package com.example.QuickOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetAllProductsOfFarmerResponseDto
{
    private String productName;
    private int productPrice;
    private int productAvailableQuantity;
}
