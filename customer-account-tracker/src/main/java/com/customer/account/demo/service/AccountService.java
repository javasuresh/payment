package com.customer.account.demo.service;

import org.springframework.http.ResponseEntity;

import com.customer.account.demo.model.AccountRequest;
import com.customer.account.demo.model.CustomerResponse;

public interface AccountService {

	ResponseEntity<Object> addAccount(AccountRequest request);
    ResponseEntity<Object> getAccount(String customerId);
    ResponseEntity<Object> updateAccount(AccountRequest request);
    ResponseEntity<Object> getAllAccount();
    ResponseEntity<Object> deleteAccount(String customerId);
}
