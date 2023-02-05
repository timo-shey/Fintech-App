package com.example.FintechApplication.model;

import com.example.FintechApplication.dto.request.TransactionRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "transactions")
public class TransactionsEntity {
    @Id
    private String id;
    private String sourceAccountId;
    private String destinationAccountId;
    private Long amountInNaira;
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = AccountEntity.class)
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    private AccountEntity sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = AccountEntity.class)
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    private AccountEntity destinationAccount;

    public TransactionsEntity(TransactionRequest tr, String sourceAccountId) {
        this.id = UUID.randomUUID().toString();
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = tr.getDestinationAccountId();
        this.amountInNaira = tr.getAmountInNaira();
        this.transactionDate = LocalDate.now();
        this.transactionTime = LocalTime.now();
        this.message = tr.getMessage();
    }
    public TransactionsEntity(TransactionRequest tr) {
        this.id = UUID.randomUUID().toString();
        this.sourceAccountId = tr.getSourceAccountId();
        this.destinationAccountId = tr.getDestinationAccountId();
        this.amountInNaira = tr.getAmountInNaira();
        this.transactionDate = LocalDate.now();
        this.transactionTime = LocalTime.now();
        this.message = tr.getMessage();
    }
}
