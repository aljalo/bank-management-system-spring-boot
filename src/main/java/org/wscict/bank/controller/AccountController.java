package org.wscict.bank.controller;

import org.springframework.web.bind.annotation.*;
import org.wscict.bank.model.Account;
import org.wscict.bank.service.AccountService;

import java.util.List;

@RestController
    @RequestMapping("/accounts")
public class AccountController {

        private final AccountService accountService;

        public AccountController(AccountService accountService){
            this.accountService = accountService;
        }

        @PostMapping
    public Account createAccount(@RequestParam String ownerName,
                                 @RequestParam double balance) {
            return accountService.createAccount(ownerName, balance);
        }
        @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id){
            return accountService.getAccountById(id);
        }
        @GetMapping
    public List<Account> getAccounts(){
            return accountService.getAllAccounts();
        }
}
