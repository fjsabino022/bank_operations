package com.fsabino.banking_business.exception;

public class ClientForbiddenException extends Exception {

    public ClientForbiddenException(String message) {
        super(message);
    }

    public ClientForbiddenException(String message, Exception e) {
        super(message, e);
    }
}
