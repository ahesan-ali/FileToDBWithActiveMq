package com.rbc.bbp0.repository;


import com.rbc.bbp0.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    Data findByAccountNumber(Long accountNumber);
    // Additional methods if needed
}