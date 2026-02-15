package org.wscict.bank.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.exception.ResourceNotFoundException;
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

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        log.info("Creating account for owner: {}", request.getOwnerName());

        Account account = new Account();
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance());
        account.setAccountStatus(AccountStatus.ACTIVE);

        Account saved = accountRepository.save(account);

        log.info("Account created successfully with ID: {}", saved.getId());

        return mapToResponse(saved);
    }

    @Override
    public AccountResponse getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found with id: " + id));

        return mapToResponse(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<AccountResponse> getAllAccounts(Pageable pageable) {

        return accountRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public long countAccounts() {
        return accountRepository.count();
    }

    // 🔥 Private Mapper Method
    private AccountResponse mapToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getOwnerName(),
                account.getBalance(),
                account.getAccountStatus()
        );
    }
}
