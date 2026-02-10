package org.wscict.bank.service.impl;

import org.springframework.stereotype.Service;
import org.wscict.bank.model.Account;
import org.wscict.bank.repository.AccountRepository;
import org.wscict.bank.service.AccountService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(String ownerName, double balance){
        Account account = new Account(ownerName, balance , " ACTIVE");
        return accountRepository.save(account);
    }
    @Override
    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }
    @Override
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
