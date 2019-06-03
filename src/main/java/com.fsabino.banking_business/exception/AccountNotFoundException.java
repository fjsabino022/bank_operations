package com.fsabino.banking_business.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
