package com.capgemini.onlineStore.validator.exception;

public class InvalidPurchaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidPurchaseException(){
    }

    public InvalidPurchaseException(String message) {
        super(message);
    }
}

