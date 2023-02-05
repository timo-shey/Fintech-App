package com.example.FintechApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "credits")
public class CreditEntity {
    @Id
    private String id;
    private String customerId;
    private LocalDate dateOfCreation;
    private Long originalTermInMonths;
    private Long remainingTermInMonths;
    private Long originalCreditAmountInNaira;
    private Long currentCreditAmountInNaira;
}
