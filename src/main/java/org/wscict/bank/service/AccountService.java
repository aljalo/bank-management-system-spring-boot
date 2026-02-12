package org.wscict.bank.service;

import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.model.Account;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);
    //Account createAccount(String ownerName, double balance);

    Account getAccountById(Long id);

    List<Account> getAllAccounts();
}
