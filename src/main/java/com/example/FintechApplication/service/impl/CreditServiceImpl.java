package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.request.CreditRequest;
import com.example.FintechApplication.dto.request.CreditUpdateRequest;
import com.example.FintechApplication.dto.request.TransactionRequest;
import com.example.FintechApplication.dto.response.CreditResponse;
import com.example.FintechApplication.exceptions.BankAccountNotFoundException;
import com.example.FintechApplication.exceptions.CreditNotFoundException;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.CreditEntity;
import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import com.example.FintechApplication.repositories.AccountRepository;
import com.example.FintechApplication.repositories.CreditsRepository;
import com.example.FintechApplication.repositories.CustomerRepository;
import com.example.FintechApplication.service.AccountService;
import com.example.FintechApplication.service.CreditService;
import com.example.FintechApplication.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CustomerRepository customerRepository;
    private final CreditsRepository creditRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final AccountService accountService;
    @Override
    public CreditResponse createCreditForCustomer(String customerId, CreditRequest newCreditRequest) throws CustomerNotFoundException {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            LocalDate creationDay = LocalDate.now();
            CreditEntity newCredit = new CreditEntity(
                    UUID.randomUUID().toString(),
                    customer.get().getId(),
                    creationDay,
                    newCreditRequest.getRuntimeInMonths(),
                    calculateRemainingRuntime(creationDay, LocalDate.now(), newCreditRequest.getRuntimeInMonths()),
                    newCreditRequest.getCreditAmountInNaira(),
                    newCreditRequest.getCreditAmountInNaira()
            );
            return new CreditResponse(creditRepository.save(newCredit));
        } else {
            throw new CustomerNotFoundException("Customer with id: " + customerId +
                    " not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Long calculateRemainingRuntime(LocalDate creationDay, LocalDate currentDay, Long orignialRuntime) {
        Long pastMonths =  ChronoUnit.MONTHS.between(creationDay, currentDay);
        return orignialRuntime - pastMonths;
    }

    @Override
    public List<CreditResponse> getAllCreditsById(String customerId) {
        List<CreditEntity> credits = creditRepository.findAllByCustomerId(customerId);
        return credits.stream()
                .map(CreditResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public CreditResponse createUpdateCredit(String creditId, CreditUpdateRequest creditUpdateRequest) throws Exception {
        Optional<CreditEntity> credit = creditRepository.findById(creditId);
        Optional<AccountEntity> sourceAccount = accountRepository.findById(creditUpdateRequest.getSourceAccountId());
        if(credit.isPresent()){
            if(sourceAccount.isPresent()){

                CreditResponse updatedCredit = createAndSaveUpdatedCredit(credit.get(), creditUpdateRequest);

                // If i would have a transaction interfaced than i could only give the transaction to the mehtod. THis would mean no need for mapping.
                TransactionsEntity savedTransaction = transactionService.saveNewTransaction(
                        createTransactionRequestFrom(creditUpdateRequest)
                );

                accountService.updateAccounts(savedTransaction);

                return updatedCredit;
            } else {
                throw new BankAccountNotFoundException();
            }
        } else {
            throw new CreditNotFoundException("Credit with the id: " + creditId + " not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<CreditResponse> getAllCredits() {
        return null;
    }

    private TransactionRequest createTransactionRequestFrom(CreditUpdateRequest creditUpdateRequest) {
        return new TransactionRequest(
                creditUpdateRequest.getAmountInNaira(),
                creditUpdateRequest.getSourceAccountId(),
                null,
                "Credit payoff"
        );
    }

    private CreditResponse createAndSaveUpdatedCredit(CreditEntity credit,
                                                      CreditUpdateRequest creditUpdateRequest) {
        CreditEntity updatedCredit = new CreditEntity(
                credit.getId(),
                credit.getCustomerId(),
                credit.getDateOfCreation(),
                credit.getOriginalTermInMonths(),
                credit.getRemainingTermInMonths(),
                credit.getOriginalCreditAmountInNaira(),
                credit.getCurrentCreditAmountInNaira() - creditUpdateRequest.getAmountInNaira()
        );
        return new CreditResponse(
                creditRepository.save(updatedCredit)
        );
    }
}
