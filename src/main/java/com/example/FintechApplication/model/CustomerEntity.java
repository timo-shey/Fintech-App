package com.example.FintechApplication.model;

import com.example.FintechApplication.dto.request.CustomerRequest;
import com.example.FintechApplication.exceptions.WrongDateFormatException;
import com.example.FintechApplication.service.impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String alias;
    private LocalDate birthday;
    private Integer rating;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public CustomerEntity( CustomerRequest cr) throws WrongDateFormatException {
        this.id = UUID.randomUUID().toString();
        this.firstName = cr.getFirstName();
        this.lastName = cr.getLastName();
        this.alias = cr.getAlias();
        this.birthday = CustomerServiceImpl.convertToDate(cr.getDateOfBirth());
        this.rating = 2;
    }

    public void setAddress(AddressEntity address){
        this.address = address;
    }
}
