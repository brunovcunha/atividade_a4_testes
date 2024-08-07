package com.iftm.client.repositories;

import com.iftm.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM tb_client WHERE income > :income", nativeQuery = true)
    List<Client> findByIncomeGreaterThan(Double income);

    @Query(value = "SELECT * FROM tb_client WHERE income < :income", nativeQuery = true)
    List<Client> findByIncomeLessThan(Double income);

    @Query(value = "SELECT * FROM tb_client WHERE income BETWEEN :minIncome AND :maxIncome", nativeQuery = true)
    List<Client> findByIncomeBetween(Double minIncome, Double maxIncome);

}
