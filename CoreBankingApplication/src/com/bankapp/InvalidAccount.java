package com.bankapp;

public class InvalidAccount extends Exception {
    public InvalidAccount(String messageString){
        super(messageString);
    }

}
