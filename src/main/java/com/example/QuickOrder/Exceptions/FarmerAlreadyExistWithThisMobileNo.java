package com.example.QuickOrder.Exceptions;

public class FarmerAlreadyExistWithThisMobileNo extends Exception{
    public FarmerAlreadyExistWithThisMobileNo()
    {
        super("This mobile Number is already registered with another farmer.");
    }
}
