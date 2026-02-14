package org.wscict.bank.dto;

import jakarta.validation.constraints.*;

public class CreateAccountRequest {

    @NotBlank(message = "Owner name must not be empty")
    @Size(min = 3, max = 50, message = "Owner name must be between 3 and 50 characters")
    private String ownerName;

    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
