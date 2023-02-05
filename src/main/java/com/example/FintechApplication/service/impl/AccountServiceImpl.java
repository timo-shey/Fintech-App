package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.response.AccountResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import com.example.FintechApplication.repositories.AccountRepository;
import com.example.FintechApplication.repositories.CustomerRepository;
import com.example.FintechApplication.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

   public final AccountRepository accountRepository;
   public final CustomerRepository customerRepository;
    @Override
    public AccountResponse saveNewAccountForCustomer(String customerId) throws CustomerNotFoundException {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            AccountEntity newAccount = new AccountEntity(
                    UUID.randomUUID().toString(),
                    customer.get().getId(),
                    0L,
                    0L,
                    new ArrayList<String>(),
                    new ArrayList<TransactionsEntity>(),
                    new ArrayList<TransactionsEntity>()
            );
            return new AccountResponse(accountRepository.save(newAccount));
        } else {
            throw new CustomerNotFoundException("Customer with id: " + customerId + " not fount", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateAccounts(TransactionsEntity transaction) throws BankAccountNotFoundException {
        AccountEntity sourceAccount = transaction.getSourceAccount();
        AccountEntity destinationAccount = transaction.getDestinationAccount();

        sourceAccount.setTransactionIds(appendTransaction(sourceAccount.getTransactionIds(), transaction.getId()));
        destinationAccount.setTransactionIds(appendTransaction(destinationAccount.getTransactionIds(), transaction.getId()));

        sourceAccount.setDebitTransactions(appendTransaction(sourceAccount.getDebitTransactions(), transaction));
        destinationAccount.setCreditTransactions(appendTransaction(destinationAccount.getCreditTransactions(), transaction));

        sourceAccount.setBalance(updateAccountBalance(sourceAccount, transaction.getAmountInNaira() * -1));
        destinationAccount.setBalance(updateAccountBalance(destinationAccount, transaction.getAmountInNaira()));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
    }

    @Override
    public Long updateAccountBalance(AccountEntity account, Long amount) {
        Long currentBalance = account.getBalanceInNaira();
        return currentBalance + amount;
    }

    @Override
    public List<AccountResponse> getAccountsBy(String customerId) {
        List<AccountEntity> accounts;
        if(customerId.equals("Institution")){
            accounts = accountRepository.findAll();
        } else {
            accounts = accountRepository.findAllByCustomerId(customerId).get();
        }

        return accounts.stream()
                .map(AccountResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long getAccountsAndCalculateSum(String customerId) {
        List<AccountResponse> accounts = getAccountsBy(customerId);
        Long sumOfBalanceInNaira = 0L;
        for(AccountResponse account : accounts){
            sumOfBalanceInNaira += account.getBalanceInNaira();
        }
        return sumOfBalanceInNaira;
    }
    public static <T> List<T> appendTransaction(List<T> oldTransactionIds, T newTransactionId) {
        List<T> newTransactionIds = new ArrayList<>();
        newTransactionIds.addAll(oldTransactionIds);
        newTransactionIds.add(newTransactionId);
        return newTransactionIds;
    }
}
