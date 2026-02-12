package org.wscict.bank.exception;

public class BadRequestException extends ApiException{

    public BadRequestException(String message) {
        super(message, "BANK_400");
    }
}
