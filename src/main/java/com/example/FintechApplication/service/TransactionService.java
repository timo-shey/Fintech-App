package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.TransactionRequest;
import com.example.FintechApplication.dto.response.PageResponse;
import com.example.FintechApplication.dto.response.TransactionsForCustomerResponse;
import com.example.FintechApplication.dto.response.TransactionsFullResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.model.TransactionsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionsFullResponse processNewTransaction(TransactionRequest request) throws BankAccountNotFoundException;
    TransactionsEntity saveNewTransaction(TransactionRequest request) throws BankAccountNotFoundException;
    List<TransactionsFullResponse> getAllTransactionsBy(String date);
    List<TransactionsForCustomerResponse> getAllTransactionsForCustomer(String customerId) throws Exception;
    PageResponse<TransactionsForCustomerResponse> getPagedTransactions(String customerId, Integer pageNo, Integer pageSize, String sortBy) throws Exception;
}
