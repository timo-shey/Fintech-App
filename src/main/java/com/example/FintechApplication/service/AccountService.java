package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.response.AccountResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    AccountResponse saveNewAccountForCustomer(String customerId) throws CustomerNotFoundException;
    void updateAccounts(TransactionsEntity transaction) throws BankAccountNotFoundException;
    Long updateAccountBalance(AccountEntity account, Long amount);
    List<AccountResponse> getAccountsBy(String customerId);
    Long getAccountsAndCalculateSum(String customerId);
}
