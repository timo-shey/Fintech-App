package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.CreditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreditResponse {
  private String id;
  private String customerId;
  private Long originalTermInMonths;
  private Long remainingTermInMonths;
  private Long originalCreditAmount;
  private Long currentCreditAmount;

  public CreditResponse(CreditEntity creditEntity){
    this.id = creditEntity.getId();
    this.customerId = creditEntity.getCustomerId();
    this.originalTermInMonths = creditEntity.getOriginalTermInMonths();
    this.remainingTermInMonths = creditEntity.getRemainingTermInMonths();
    this.originalCreditAmount = creditEntity.getOriginalCreditAmountInNaira();
    this.currentCreditAmount = creditEntity.getCurrentCreditAmountInNaira();
  }
}
