package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.request.TransactionRequest;
import com.example.FintechApplication.dto.response.PageResponse;
import com.example.FintechApplication.dto.response.TransactionsForCustomerResponse;
import com.example.FintechApplication.dto.response.TransactionsFullResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import com.example.FintechApplication.repositories.AccountRepository;
import com.example.FintechApplication.repositories.CustomerRepository;
import com.example.FintechApplication.repositories.TransactionRepository;
import com.example.FintechApplication.service.AccountService;
import com.example.FintechApplication.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    private static Map<String, Comparator<TransactionsForCustomerResponse>> sortby = new HashMap<>();

    @Override
    public TransactionsFullResponse processNewTransaction(TransactionRequest request) throws BankAccountNotFoundException {
        TransactionsEntity newTransaction = saveNewTransaction(request);
        accountService.updateAccounts(newTransaction);
        return new TransactionsFullResponse (newTransaction);
    }

    @Override
    public TransactionsEntity saveNewTransaction(TransactionRequest request) throws BankAccountNotFoundException {
        return null;
    }

    @Override
    public List<TransactionsFullResponse> getAllTransactionsBy(String date) {
        return null;
    }

    @Override
    public List<TransactionsForCustomerResponse> getAllTransactionsForCustomer(String customerId) throws Exception {
        return null;
    }

    private CustomerEntity getCustomer(String sourceAccountId) {
        AccountEntity account = accountRepository.getById(sourceAccountId);
        return customerRepository.getById(account.getCustomerId());
    }

    public enum TransactionType{
        DEPOSIT(0),WITHDRAWAL(1);
        public final int value;
        private TransactionType(int value){
            this.value = value;
        }
    }

    private PageResponse<TransactionsForCustomerResponse> getPage(Integer pageNo, Integer pageSize, List<TransactionsForCustomerResponse> transactions, String sortBy ){
        int skipPreviousTransactions = (pageNo - 1) * pageSize;
        List<TransactionsForCustomerResponse> transactionsSorted = transactions.stream()
                .sorted(getComparator(sortBy))
                .collect(Collectors.toList());

        List<TransactionsForCustomerResponse> transactionsPage = transactionsSorted
                .stream()
                .skip(skipPreviousTransactions)
                .limit(pageSize)
                .collect(Collectors.toList());
        return new PageResponse<>(pageNo, transactionsPage.size(), transactions.size(), transactionsPage);
    }

    private Comparator<TransactionsForCustomerResponse> getComparator(String sortingFunc){
        return sortby.get(sortingFunc);
    };

    private void initSortbyMap(){
        sortby.put("sender_firstName", Comparator.comparing(TransactionsForCustomerResponse::getCustomerFirstName));
        sortby.put("sender_lastName", Comparator.comparing(TransactionsForCustomerResponse::getCustomerLastName));
        sortby.put("recipient_firstName", Comparator.comparing(TransactionsForCustomerResponse::getCustomerFirstNameDestination));
        sortby.put("recipient_lastName", Comparator.comparing(TransactionsForCustomerResponse::getCustomerLastNameDestination));
    }
}
