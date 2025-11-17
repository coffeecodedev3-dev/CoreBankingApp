package com.bankapp;

public class InsufficientAmount extends Exception{
    public InsufficientAmount(String message){
        super(message);
    }

}
