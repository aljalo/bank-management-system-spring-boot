package org.wscict.bank.dto;

public class AccountResponse {

    private Long id;
    private String ownerName;
    private double balance;
    private String status;

    public AccountResponse(Long id, String ownerName, double balance, String status){
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
        this.status = status;
    }

    public Long getId(){
        return id;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public double getBalance(){
        return balance;
    }
    public String getStatus(){
        return status;
    }
}
