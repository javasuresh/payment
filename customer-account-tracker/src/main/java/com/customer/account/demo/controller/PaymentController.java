package com.customer.account.demo.controller;


import io.swagger.annotations.Api;
import lombok.extern.flogger.Flogger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.customer.account.demo.config.SwaggerConfig;

import com.customer.account.demo.model.PaymentConfirmRequest;
import com.customer.account.demo.service.PaymentService;
import com.customer.account.demo.serviceImpl.PaymentServiceImpl;

import javax.validation.Valid;
import static com.customer.account.demo.constant.ApiHeaderConstants.SURESH_CHANNEL;

@RestController
@RequestMapping("/wallets/v1.0")
@Validated
@Api(value = "/wallets", tags = {SwaggerConfig.ServiceTags.WALLET_SERVICE})
//@Flogger
public class PaymentController implements PaymentApi {
    private final PaymentService paymentService;

    public PaymentController(PaymentServiceImpl paymentService){
        this.paymentService=paymentService;
    }

    @Override
    @PostMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> confirmPayment(@RequestHeader(SURESH_CHANNEL) String neomChannel,@Valid @RequestBody PaymentConfirmRequest request){
        return paymentService.confirmPayment(request);
    }

}
