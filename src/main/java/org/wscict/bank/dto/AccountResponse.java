package org.wscict.bank.dto;

import jakarta.validation.constraints.NotBlank;
import org.wscict.bank.model.AccountStatus;

public class AccountResponse {

    private Long id;
    @NotBlank(message = "Owner name must not be empty")
    private String ownerName;
    private double balance;
    private AccountStatus accountStatus;

    public AccountResponse(Long id, String ownerName, double balance, AccountStatus accountStatus) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    //public String getStatus(){
    //    return status;
    //}
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }
}
