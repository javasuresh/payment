package com.customer.account.demo.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestHeader;

import com.customer.account.demo.model.PaymentConfirmRequest;
import com.customer.account.demo.model.PaymentConfirmResponse;

import static com.customer.account.demo.constant.ApiHeaderConstants.SURESH_CHANNEL;


public interface PaymentApi {
    @ApiOperation(value = "Confirm Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaymentConfirmResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> confirmPayment(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@Valid  PaymentConfirmRequest request);
}
