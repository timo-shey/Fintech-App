package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.AccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class AccountsWithBalanceResponse {
  private String id;
  private Long balanceInCents;

  public AccountsWithBalanceResponse(AccountEntity ae){
   this.id = ae.getId();
   this.balanceInCents = ae.getBalanceInNaira();
  }

}
