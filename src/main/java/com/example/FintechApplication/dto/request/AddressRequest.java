package com.example.FintechApplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddressRequest {
  private String city;
  private String street;
  private String state;
  private String zipCode;
  private String country;
}
