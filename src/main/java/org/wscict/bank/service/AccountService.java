package org.wscict.bank.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse getAccountById(Long id);

    List<AccountResponse> getAllAccounts();

    Page<AccountResponse> getAllAccounts(Pageable pageable);

    long countAccounts();
}
