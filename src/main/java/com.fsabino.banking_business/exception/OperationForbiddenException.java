package com.fsabino.banking_business.exception;

public class OperationForbiddenException extends RuntimeException {

    public OperationForbiddenException(String message) {
        super(message);
    }

    public OperationForbiddenException(String message, Exception e) {
        super(message, e);
    }
}
