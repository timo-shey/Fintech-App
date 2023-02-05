package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.request.TransactionRequest;
import com.example.FintechApplication.dto.response.PageResponse;
import com.example.FintechApplication.dto.response.TransactionsForCustomerResponse;
import com.example.FintechApplication.dto.response.TransactionsFullResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public TransactionsFullResponse newTransactions(@RequestBody TransactionRequest transactionRequest)throws BankAccountNotFoundException {
        return transactionService.processNewTransaction(transactionRequest);
    }

    @GetMapping("/transactions")
    public List<TransactionsFullResponse> findAllTransactions(@RequestParam(name = "date") String date){
        return transactionService.getAllTransactionsBy(date);
    }


    @GetMapping("/transactions/{id}")
    public PageResponse<TransactionsForCustomerResponse> getAllTransactions(
            @RequestParam(name = "pageno", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortby", defaultValue = "sender_firstname") String sortBy,
            @PathVariable(name = "id") String customerId
    ) throws Exception{
        return transactionService.getPagedTransactions(customerId, pageNo, pageSize, sortBy);
    }
}
