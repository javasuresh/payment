package com.customer.account.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.account.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


    Customer findByCustomerId(String customerId);
}
