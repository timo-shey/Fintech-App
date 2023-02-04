package com.example.FintechApplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {
  private Long creditAmountInNaira;
  private Long runtimeInMonths;
}
