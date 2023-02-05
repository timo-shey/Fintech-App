package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.request.AddressRequest;
import com.example.FintechApplication.dto.request.CustomerRequest;
import com.example.FintechApplication.dto.response.CustomerAndAddressResponse;
import com.example.FintechApplication.dto.response.CustomerResponse;
import com.example.FintechApplication.exceptions.WrongDateFormatException;
import com.example.FintechApplication.model.AddressEntity;
import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.repositories.AddressRepository;
import com.example.FintechApplication.repositories.CustomerRepository;
import com.example.FintechApplication.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    public static LocalDate convertToDate(String date) throws WrongDateFormatException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date,formatter);
        } catch(Exception e){
            throw new WrongDateFormatException("The Data of Birth needs to be in the format dd/MM/yyyy", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public CustomerAndAddressResponse createCustomerWithAddress(CustomerRequest customerRequest, AddressRequest addressRequest) throws WrongDateFormatException {
        CustomerEntity newCustomer = new CustomerEntity(customerRequest);
        AddressEntity newAddress = new AddressEntity(
                UUID.randomUUID().toString(),
                newCustomer.getId(),
                addressRequest.getCity(),
                addressRequest.getStreet(),
                addressRequest.getState(),
                addressRequest.getZipCode(),
                addressRequest.getCountry(),
                newCustomer
        );
        newCustomer.setAddress(newAddress);
        return new CustomerAndAddressResponse(customerRepository.save(newCustomer), addressRepository.save(newAddress));
    }

    @Override
    public List<CustomerResponse> findAllCustomersByName(String firstName, String filtertag) {
        List<CustomerEntity> customers;
        if(Objects.equals(filtertag, "true")){
            customers = customerRepository.findAllByName(firstName);
        } else {
            customers = customerRepository.findAllByNameOrdered(firstName);
        }

        return customers.stream()
                .map(customerEntity -> new CustomerResponse(customerEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponse = customers.stream()
                .map(customerEntity -> new CustomerResponse(customerEntity))
                .collect(Collectors.toList());
        return customerResponse;
    }

    @Override
    public Map<Integer, List<CustomerResponse>> findAllCustomersGroupedBy(String groupedBy) {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<CustomerResponse> customersResponse = customers.stream()
                .map(customerEntity -> new CustomerResponse(customerEntity))
                .collect(Collectors.toList());

        return customersResponse.stream()
                .collect(Collectors.groupingBy(customerResponse -> customerResponse.getRating()));
    }
}
