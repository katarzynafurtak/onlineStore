package com.capgemini.onlineStore.validator.exception;

public class InvalidTransactionException extends RuntimeException {

    public InvalidTransactionException(){
    }

    public InvalidTransactionException(String message) {
        super(message);
    }
}

