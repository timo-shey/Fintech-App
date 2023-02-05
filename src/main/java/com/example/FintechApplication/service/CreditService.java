package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.CreditRequest;
import com.example.FintechApplication.dto.request.CreditUpdateRequest;
import com.example.FintechApplication.dto.response.CreditResponse;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CreditService {
    CreditResponse createCreditForCustomer(String customerId, CreditRequest newCreditRequest) throws CustomerNotFoundException;
    Long calculateRemainingRuntime(LocalDate creationDay, LocalDate currentDay, Long orignialRuntime);
    List<CreditResponse> getAllCreditsById(String customerId);
    CreditResponse createUpdateCredit(String creditId, CreditUpdateRequest creditUpdateRequest) throws Exception;
    List<CreditResponse> getAllCredits();
}
