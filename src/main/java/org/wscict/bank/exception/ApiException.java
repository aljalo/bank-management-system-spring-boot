package org.wscict.bank.exception;

public class ApiException extends RuntimeException{

    private final String errorCode;

    protected ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }
}
