package org.wscict.bank.exception;


public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super("BANK_404", message);
    }
}
