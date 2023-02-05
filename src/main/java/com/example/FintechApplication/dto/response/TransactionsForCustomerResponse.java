package com.example.FintechApplication.dto.response;

import com.example.FintechApplication.model.CustomerEntity;
import com.example.FintechApplication.model.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionsForCustomerResponse {
  private String accountSourceId;
  private String customerFirstName;
  private String customerLastName;
  private String accountDestinationId;
  private String customerFirstNameDestination;
  private String customerLastNameDestination;

  public TransactionsForCustomerResponse(TransactionsEntity transaction, CustomerEntity sourceCustomer, CustomerEntity destinationCustomer){
      this.accountSourceId = transaction.getSourceAccountId();
      this.customerFirstName = sourceCustomer.getFirstName();
      this.customerLastName = sourceCustomer.getLastName();
      this.accountDestinationId = transaction.getDestinationAccountId();
      this.customerFirstNameDestination = destinationCustomer.getFirstName();
      this.customerLastNameDestination = destinationCustomer.getLastName();
    }
}
