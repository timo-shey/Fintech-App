package com.example.FintechApplication.repositories;

import com.example.FintechApplication.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    @Query("SELECT cus FROM CustomerEntity cus WHERE cus.lastName = :lastName")
    List<CustomerEntity> findAllByName(String lastName);

    @Query("SELECT cus FROM CustomerEntity cus WHERE cus.lastName = :lastName ORDER BY firstName")
    List<CustomerEntity> findAllByNameOrdered(String lastName);
}
