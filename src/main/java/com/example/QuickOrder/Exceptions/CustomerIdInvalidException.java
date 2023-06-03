package com.example.QuickOrder.Exceptions;

public class CustomerIdInvalidException extends Exception{
    public CustomerIdInvalidException()
    {
        super("Invalid customer id");
    }
}
