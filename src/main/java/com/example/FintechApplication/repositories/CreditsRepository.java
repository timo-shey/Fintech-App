package com.example.FintechApplication.repositories;

import com.example.FintechApplication.model.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditsRepository extends JpaRepository<CreditEntity, String> {
    @Query("SELECT ALL cre FROM CreditsEntity cre WHERE cre.customerId = :customerId")
    List<CreditEntity> findAllByCustomerId(String customerId);

    @Query("SELECT ALL cre FROM CreditsEntity cre WHERE cre.remainingTermInMonths < 0.0")
    List<CreditEntity> findAllThatExceededOriginalTerm();
}
