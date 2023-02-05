package com.example.FintechApplication.repositories;

import com.example.FintechApplication.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    @Query("SELECT acc FROM AccountEntity acc WHERE acc.customerId = :customerId")
    Optional<AccountEntity> findByCustomerId(String customerId);

    @Query("SELECT ALL acc FROM AccountEntity acc WHERE acc.customerId = :customerId")
    Optional<List<AccountEntity>> findAllByCustomerId(String customerId);
}
