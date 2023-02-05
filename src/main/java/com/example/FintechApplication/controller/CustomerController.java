package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.request.CustomerRequest;
import com.example.FintechApplication.dto.response.CustomerAndAddressResponse;
import com.example.FintechApplication.dto.response.CustomerResponse;
import com.example.FintechApplication.exceptions.WrongDateFormatException;
import com.example.FintechApplication.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers/{lastName}")
    public List<CustomerResponse> getAllCustomersByLastName(@PathVariable String lastName,
                                                            @RequestParam(required = false) String sort){
        return customerService.findAllCustomersByName(lastName, sort);
    }

    @GetMapping("/customers/grouped")
    public Map<Integer, List<CustomerResponse>> getAllCustomersGroupedBy(@RequestParam(name = "grouped") String groupedBy){
        return customerService.findAllCustomersGroupedBy(groupedBy);
    }

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.findAllCustomers();
    }

    @PostMapping("/customers")
    public CustomerAndAddressResponse createCustomer(@RequestBody CustomerRequest customerRequest)throws WrongDateFormatException {
        return customerService.createCustomerWithAddress(customerRequest, customerRequest.getAddress());
    }
}
