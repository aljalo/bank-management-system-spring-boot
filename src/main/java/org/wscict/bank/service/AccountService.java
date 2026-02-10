package org.wscict.bank.service;

import org.wscict.bank.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(String ownerName, double balance);

    Account getAccountById(Long id);

    List<Account> getAllAccounts();
}
