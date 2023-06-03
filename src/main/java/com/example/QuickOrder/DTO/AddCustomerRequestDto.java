package com.example.QuickOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCustomerRequestDto
{
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private int customerAge;
}
