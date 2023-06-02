package com.example.QuickOrder.Exceptions;

public class ProductNameDoesNotExistException extends Exception{
    public ProductNameDoesNotExistException(){
        super("Product name does not exist");
    }
}
