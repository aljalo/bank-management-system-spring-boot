package org.wscict.bank.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.model.Account;
import org.wscict.bank.payload.ApiResponse;
import org.wscict.bank.payload.PaginationResponse;
import org.wscict.bank.service.AccountService;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ✅ Create Account
    @PostMapping
    public ApiResponse<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        AccountResponse response = accountService.createAccount(request);
        return new ApiResponse<>(true, response);
    }

    // ✅ Get All Accounts (Pagination)
    @GetMapping
    public PaginationResponse<AccountResponse> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Account> accountPage = accountService.getAllAccounts(pageable);

        var accounts = accountPage.getContent().stream()
                .map(this::mapToResponse)
                .toList();

        return new PaginationResponse<>(
                true,
                accounts,
                accountPage.getNumber(),
                accountPage.getSize(),
                accountPage.getTotalElements(),
                accountPage.getTotalPages()
        );
    }

    // ✅ Get Account By Id
    @GetMapping("/{id}")
    public ApiResponse<AccountResponse> getAccountById(@PathVariable Long id) {

        Account account = accountService.getAccountById(id);
        return new ApiResponse<>(true, mapToResponse(account));
    }

    // 🔹 Private Mapper
    private AccountResponse mapToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getOwnerName(),
                account.getBalance(),
                account.getAccountStatus()
        );
    }
}
