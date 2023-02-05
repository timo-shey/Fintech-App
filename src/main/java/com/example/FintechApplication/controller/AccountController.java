package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.response.AccountResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/accounts/{id}")
    public AccountResponse createNewAccountForId(@PathVariable(name ="id") String customerId) throws CustomerNotFoundException {
        return accountService.saveNewAccountForCustomer(customerId);
    }

    @GetMapping("/accounts/{id}")
    public List<AccountResponse> getAllAccounts(@PathVariable (name ="id") String customerId)throws BankAccountNotFoundException {
        return accountService.getAccountsBy(customerId);
    }

    @GetMapping("/accounts/{id}/balance")
    public Long getSumBalanceOfAllAccountsForUser(@PathVariable (name = "id") String customerId){
        return accountService.getAccountsAndCalculateSum(customerId);
    }

    @GetMapping("/accounts/balance")
    public Long getSumBalanceOfAllAccounts(){
        return accountService.getAccountsAndCalculateSum("Institution");
    }
}
