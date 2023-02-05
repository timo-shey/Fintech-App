package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.AddressRequest;
import com.example.FintechApplication.dto.request.CustomerRequest;
import com.example.FintechApplication.dto.response.CustomerAndAddressResponse;
import com.example.FintechApplication.dto.response.CustomerResponse;
import com.example.FintechApplication.exceptions.WrongDateFormatException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CustomerService {
    CustomerAndAddressResponse createCustomerWithAddress(CustomerRequest customerRequest, AddressRequest addressRequest) throws WrongDateFormatException;
    List<CustomerResponse> findAllCustomersByName(String firstName, String filtertag);
    List<CustomerResponse> findAllCustomers();
    Map<Integer, List<CustomerResponse>> findAllCustomersGroupedBy(String groupedBy);
}
