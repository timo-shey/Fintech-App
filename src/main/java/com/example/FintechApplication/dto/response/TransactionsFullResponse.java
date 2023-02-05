package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.AccountEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionsFullResponse {
  private String id;
  private String sourceAccountId;
  private AccountEntity sourceAccount;
  private String destinationAccountId;
  private Long amountInNaira;
  private LocalDate transactionDate;
  private LocalTime transactionTime;

  public TransactionsFullResponse(TransactionsEntity transaction){
    this.id = transaction.getId();
    this.sourceAccount = transaction.getSourceAccount();
    this.sourceAccountId = transaction.getSourceAccountId();
    this.destinationAccountId = transaction.getDestinationAccountId();
    this.amountInNaira = transaction.getAmountInNaira();
    this.transactionDate = transaction.getTransactionDate();
    this.transactionTime = transaction.getTransactionTime();
  }

}
