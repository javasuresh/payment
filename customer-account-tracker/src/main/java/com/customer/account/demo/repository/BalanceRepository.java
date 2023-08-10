package com.customer.account.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.account.demo.entity.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance,Integer> {
}
