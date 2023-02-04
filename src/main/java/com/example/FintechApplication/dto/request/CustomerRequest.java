package com.example.FintechApplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable{
  private String firstName;
  private String lastName;
  private String alias;
  private String dateOfBirth; // MM/dd/yyyy
  private AddressRequest address;
}
