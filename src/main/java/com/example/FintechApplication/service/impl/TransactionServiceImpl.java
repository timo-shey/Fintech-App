package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.request.TransactionRequest;
import com.example.FintechApplication.dto.response.PageResponse;
import com.example.FintechApplication.dto.response.TransactionsForCustomerResponse;
import com.example.FintechApplication.dto.response.TransactionsFullResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import com.example.FintechApplication.repositories.AccountRepository;
import com.example.FintechApplication.repositories.CustomerRepository;
import com.example.FintechApplication.repositories.TransactionRepository;
import com.example.FintechApplication.service.AccountService;
import com.example.FintechApplication.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    private static final Map<String, Comparator<TransactionsForCustomerResponse>> sortby = new HashMap<>();

    @Override
    public TransactionsFullResponse processNewTransaction(TransactionRequest request) throws BankAccountNotFoundException {
        TransactionsEntity newTransaction = saveNewTransaction(request);
        accountService.updateAccounts(newTransaction);
        return new TransactionsFullResponse (newTransaction);
    }

    @Override
    public TransactionsEntity saveNewTransaction(TransactionRequest request) throws BankAccountNotFoundException {
        Optional<AccountEntity> sourceAccount = accountRepository.findById(request.getSourceAccountId());
        Optional<AccountEntity> destinationAccount = accountRepository.findById(request.getDestinationAccountId());

        if(sourceAccount.isPresent() && destinationAccount.isPresent()){
            TransactionsEntity newTransaction = new TransactionsEntity(
                    UUID.randomUUID().toString(),
                    sourceAccount.get().getId(),
                    destinationAccount.get().getId(),
                    request.getAmountInNaira(),
                    LocalDate.now(),
                    LocalTime.now(),
                    request.getMessage(),
                    sourceAccount.get(),
                    destinationAccount.get()
            );
            transactionRepository.save(newTransaction);
            return newTransaction;

        } else {
            throw new BankAccountNotFoundException();
        }
    }

    @Override
    public List<TransactionsFullResponse> getAllTransactionsBy(String date) {
        LocalDate localdate = LocalDate.parse(date);
        List<TransactionsEntity> transactions = transactionRepository.findAllByDate(localdate);
        return transactions.stream()
                .map(TransactionsFullResponse::new).
                collect(Collectors.toList());
    }

    @Override
    public List<TransactionsForCustomerResponse> getAllTransactionsForCustomer(String customerId) throws Exception {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        if(customer.isEmpty())
            throw new CustomerNotFoundException("Customer with id: " + customerId + "not found", HttpStatus.BAD_REQUEST);

        Optional<List<AccountEntity>> accounts = accountRepository.findAllByCustomerId(customerId);
        if(accounts.isEmpty())
            throw new BankAccountNotFoundException();

        List<String> transactions = accounts.get().stream()
                .flatMap(account -> account.getTransactionIds().stream())
                .collect(Collectors.toList());

        return createTransactionsList(transactions);
    }

    @Override
    public PageResponse<TransactionsForCustomerResponse> getPagedTransactions(String customerId, Integer pageNo, Integer pageSize, String sortBy) throws Exception {
        List<TransactionsForCustomerResponse> allTransactions =  getAllTransactionsForCustomer(customerId);
        return getPage(pageNo, pageSize, allTransactions, sortBy);
    }

    private List<TransactionsForCustomerResponse> createTransactionsList(List<String> transactionIds){
        List<TransactionsEntity> transactions = transactionRepository.findAllById(transactionIds);
        List<TransactionsForCustomerResponse> result = new ArrayList<>();
        for(TransactionsEntity transaction : transactions){
            result.add(new TransactionsForCustomerResponse(
                            transaction,
                            getCustomer(transaction.getSourceAccountId()),
                            getCustomer(transaction.getDestinationAccountId())
                    )
            );
        }
        return result;
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
