package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.AddressEntity;
import com.example.FintechApplication.model.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerAndAddressResponse {
  private String customerId;
  private String firstName;
  private String lastName;
  private String alias;
  private Integer rating;
  private AddressResponse address;

  public CustomerAndAddressResponse(CustomerEntity customer, AddressEntity address){
    this.customerId = customer.getId();
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
    this.alias = customer.getAlias();
    this.rating = customer.getRating();
    this.address = new AddressResponse(address);
  }
}
