package com.example.FintechApplication.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class CreditNotFoundException extends Exception {
  private final String errorMessage;
  private final HttpStatus errorStatus;

}

