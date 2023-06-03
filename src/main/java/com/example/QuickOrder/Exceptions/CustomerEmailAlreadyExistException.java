package com.example.QuickOrder.Exceptions;

public class CustomerEmailAlreadyExistException extends Exception{
    public CustomerEmailAlreadyExistException()
    {
        super("This email is registered with another customer");
    }
}
