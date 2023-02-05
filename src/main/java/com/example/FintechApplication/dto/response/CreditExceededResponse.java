package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.CreditEntity;
import com.example.FintechApplication.model.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreditExceededResponse {

  private Long originalCreditAmountInNaira;
  private Long currentCreditAmountInNaira;
  private String firstName;
  private String lastName;

  public CreditExceededResponse(CreditEntity credit, CustomerEntity customer){
    this.originalCreditAmountInNaira = credit.getOriginalCreditAmountInNaira();
    this.currentCreditAmountInNaira = credit.getOriginalCreditAmountInNaira();
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
  }
}
