package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AccountResponse {
  private String id;
  private String customerId;
  private Long balanceInCent;
  private Long sumOfTransactions;
  private List<String> transactionIds;
  private List<TransactionsEntity> debitTransactions;
  private List<TransactionsEntity> creditTransactions;

  public AccountResponse(AccountEntity accountEntity){
    this.id = accountEntity.getId();
    this.customerId = accountEntity.getCustomerId();
    this.balanceInCent = accountEntity.getBalanceInNaira();
    this.sumOfTransactions = accountEntity.getSumOfTransactions();
    this.transactionIds = accountEntity.getTransactionIds();
    this.creditTransactions = accountEntity.getCreditTransactions();
    this.debitTransactions = accountEntity.getDebitTransactions();
  }

}
