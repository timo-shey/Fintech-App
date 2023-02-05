package com.example.FintechApplication.model;

import com.example.FintechApplication.dto.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    private String id;
    private String customerId;
    private String city;
    private String street;
    private String state;
    private String zipCode;
    private String country;
    @OneToOne(mappedBy = "address")
    private CustomerEntity customer;

    public AddressEntity(String customerId){
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.city = null;
        this.street = null;
        this.state = null;
        this.zipCode = null;
        this.country = null;
        new CustomerEntity();
    }

    public AddressEntity(AddressRequest ar){
        this.id = UUID.randomUUID().toString();
        this.city = ar.getCity();
        this.street = ar.getStreet();
        this.state = ar.getState();
        this.zipCode = ar.getZipCode();
        this.country = ar.getCountry();
        new CustomerEntity();
    }
}
