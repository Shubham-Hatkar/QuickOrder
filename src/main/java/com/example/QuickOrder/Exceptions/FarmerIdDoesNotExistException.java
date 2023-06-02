package com.example.QuickOrder.Exceptions;

public class FarmerIdDoesNotExistException extends Exception{
    public FarmerIdDoesNotExistException()
    {
        super("Farmer Id does not exist");
    }
}
