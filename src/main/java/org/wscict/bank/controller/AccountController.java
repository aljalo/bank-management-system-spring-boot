package org.wscict.bank.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.model.Account;
import org.wscict.bank.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

   @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request){
        Account account = accountService.createAccount(
                request.getOwnerName(),
                request.getBalance()
        );
        return mapToResponse(account);
   }

   @GetMapping("/{id}")
   public AccountResponse getAccountById(@PathVariable Long id){
        Account account = accountService.getAccountById(id);
        return mapToResponse(account);
   }
   @GetMapping
   public List<AccountResponse> getAllAccounts(){
        return accountService.getAllAccounts()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
   }

   //
   private AccountResponse mapToResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getOwnerName(),
                account.getBalance(),
                account.getStatus()
        );
   }
}
