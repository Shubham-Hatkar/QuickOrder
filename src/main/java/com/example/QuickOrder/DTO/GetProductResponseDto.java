package com.example.QuickOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetProductResponseDto
{
    private String productName;
    private int productPrice;
    private int productAvailableQuantity;
    private String productOwnerName;
    private String productOwnerMobNo;
}
