package com.capgemini.onlineStore.validator.exception;

public class InvalidPurchaseException extends RuntimeException {

    public InvalidPurchaseException(){
    }

    public InvalidPurchaseException(String message) {
        super(message);
    }
}

