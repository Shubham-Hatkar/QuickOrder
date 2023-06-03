package com.example.QuickOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemoveProductFromCartDto
{
    private int productId;
    private int customerId;
}