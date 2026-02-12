package org.wscict.bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private double balance;
    //private String status;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
       // this.status = status;
        this.accountStatus = AccountStatus.ACTIVE;
    }

    //
    public Long getId() {
        return id;
    }

    //
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    //
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    //
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
