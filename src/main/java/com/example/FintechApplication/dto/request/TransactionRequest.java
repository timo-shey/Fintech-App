package com.example.FintechApplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
  private Long amountInNaira;
  private String sourceAccountId;
  private String destinationAccountId;
  private String message;
}
