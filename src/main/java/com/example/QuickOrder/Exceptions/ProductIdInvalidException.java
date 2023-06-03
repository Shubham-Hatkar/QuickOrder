package com.example.QuickOrder.Exceptions;

public class ProductIdInvalidException extends Exception{
    public ProductIdInvalidException()
    {
        super("product id is invalid");
    }
}
