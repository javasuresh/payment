package com.customer.account.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.account.demo.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    @Query("select max(t.transactionId) from Transaction t")
    String findMaxTransactionId();
}
