package com.example.store.exceptions;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String message) {
        super(message);
    }
}
