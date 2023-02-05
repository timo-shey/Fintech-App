package com.example.FintechApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    private String id;
    private String customerId;
    private Long balanceInNaira;
    private Long sumOfTransactions;

    @ElementCollection
    private List<String> transactionIds;
    @OneToMany(mappedBy = "sourceAccount")
    @JasonManagedReference
    private List<TransactionsEntity> debitTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    @JsonManagedReference
    private List<TransactionsEntity> creditTransactions;

    public void setCreditTransactions(List<TransactionsEntity> newCreditTransactions) {
        this.creditTransactions = newCreditTransactions;
    }

    public void setDebitTransactions(List<TransactionsEntity> newDebitTransactions){
        this.debitTransactions = newDebitTransactions;
    }

    public void setTransactionIds(List<String> newTransactionIds){
        this.transactionIds = newTransactionIds;
    }

    public void setBalance(Long newBalanceInNaira){
        this.balanceInNaira = newBalanceInNaira;
    }
}
