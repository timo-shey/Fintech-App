package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.request.CreditRequest;
import com.example.FintechApplication.dto.request.CreditUpdateRequest;
import com.example.FintechApplication.dto.response.CreditResponse;
import com.example.FintechApplication.exceptions.CustomerNotFoundException;
import com.example.FintechApplication.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/credits/{id}")
    public CreditResponse createCreditForCustomer(
            @PathVariable(name ="id") String customerId,
            @RequestBody CreditRequest newCreditRequest
    )throws CustomerNotFoundException {
        return creditService.createCreditForCustomer(customerId, newCreditRequest);
    }

    @GetMapping("/credits/{id}")
    public List<CreditResponse> getAllCredits(
            @PathVariable (name="id") String customerId
    ){
        return creditService.getAllCreditsById(customerId);
    }

    @PutMapping("/credits/{id}")
    public CreditResponse payoffCredit(
            @PathVariable String id,
            @RequestBody CreditUpdateRequest creditUpdateRequest
    ) throws Exception{
        return creditService.createUpdateCredit(id, creditUpdateRequest);
    }

    @GetMapping("/credits")
    public List<CreditResponse> getAllCredits(){
        return creditService.getAllCredits();
    }
}
