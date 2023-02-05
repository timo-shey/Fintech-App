package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddressResponse {
  private String city;
  private String street;
  private String state;
  private String zipCode;
  private String country;

  public AddressResponse(AddressEntity ae){
    this.city = ae.getCity();
    this.street = ae.getStreet();
    this.state = ae.getState();
    this.zipCode = ae.getZipCode();
    this.country = ae.getZipCode();
  }

}
