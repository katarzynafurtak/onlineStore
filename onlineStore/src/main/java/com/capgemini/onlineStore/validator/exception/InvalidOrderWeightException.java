package com.capgemini.onlineStore.validator.exception;

public class InvalidOrderWeightException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidOrderWeightException(){
    }

    public InvalidOrderWeightException(String message) {
        super(message);
    }
}
