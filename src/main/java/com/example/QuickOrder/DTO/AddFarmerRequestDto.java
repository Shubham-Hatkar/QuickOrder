package com.example.QuickOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFarmerRequestDto
{
    private String name;
    private int age;
    private String address;
    private String phoneNo;
}
