package org.wscict.bank.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.exception.ResourceNotFoundException;
import org.wscict.bank.mapper.AccountMapper;
import org.wscict.bank.model.Account;
import org.wscict.bank.model.AccountStatus;
import org.wscict.bank.repository.AccountRepository;
import org.wscict.bank.service.AccountService;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log =
    LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountMapper accountMapper
                              ){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        Account account = new Account();
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance());
        account.setAccountStatus(AccountStatus.ACTIVE);

        Account saved = accountRepository.save(account);

        return new AccountResponse(
                saved.getId(),
                saved.getOwnerName(),
                saved.getBalance(),
                saved.getAccountStatus()
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

    @Override
    public Page<Account> getAllAccounts(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    @Override
    public long countAccounts(){
        return accountRepository.count();
    }
}
