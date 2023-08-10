package com.customer.account.demo.service;

import org.springframework.http.ResponseEntity;

import com.customer.account.demo.model.AccountRequest;
import com.customer.account.demo.model.CustomerResponse;

public interface CustomerService {

    
    
    
    ResponseEntity<Object> addCustomer(CustomerResponse request);
    ResponseEntity<Object> getCustomer(String customerId);
    ResponseEntity<Object> updateCustomer(CustomerResponse request);
    ResponseEntity<Object> getAllCustomer();
    ResponseEntity<Object> deleteCustomer(String customerId);
}
