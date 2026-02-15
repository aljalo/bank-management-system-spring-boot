package org.wscict.bank.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
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

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        return new ApiResponse<>(
                true,
                accountService.createAccount(request),
                "Account created successfully"
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<AccountResponse> getAccountById(@PathVariable Long id) {

        return new ApiResponse<>(
                true,
                accountService.getAccountById(id),
                "Account fetched successfully"
        );
    }

    @GetMapping
    public PaginationResponse<AccountResponse> getAllAccounts(Pageable pageable) {

        Page<AccountResponse> page = accountService.getAllAccounts(pageable);

        return new PaginationResponse<>(
                true,
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
