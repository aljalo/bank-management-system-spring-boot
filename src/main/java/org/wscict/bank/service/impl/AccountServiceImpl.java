package org.wscict.bank.service.impl;

import org.springframework.stereotype.Service;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.exception.AccountNotFoundException;
import org.wscict.bank.exception.ResourceNotFoundException;
import org.wscict.bank.model.Account;
import org.wscict.bank.model.AccountStatus;
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
    public AccountResponse createAccount(CreateAccountRequest request){
        Account account = new Account();
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance());
        account.setAccountStatus(AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);

        return new AccountResponse(
                savedAccount.getId(),
                savedAccount.getOwnerName(),
                savedAccount.getBalance(),
                savedAccount.getAccountStatus()
        );
    }
    @Override
    public Account getAccountById(Long id){

       // return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }
    @Override
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
