package com.example.FintechApplication.repositories;

import com.example.FintechApplication.model.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsEntity, String> {
    @Query("SELECT ALL t FROM TransactionsEntity t WHERE t.transactionDate = :date")
    List<TransactionsEntity> findAllByDate(LocalDate date);
}