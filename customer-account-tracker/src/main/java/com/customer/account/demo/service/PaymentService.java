package com.customer.account.demo.service;

import org.springframework.http.ResponseEntity;

import com.customer.account.demo.model.PaymentConfirmRequest;

public interface PaymentService {

    ResponseEntity<Object> confirmPayment(PaymentConfirmRequest request);
}
