package com.example.FintechApplication.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BankAccountNotFoundException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

  public BankAccountNotFoundException(){
    this.errorMessage = "Bank Account not found";
    this.errorStatus = HttpStatus.BAD_REQUEST;
  }

}
