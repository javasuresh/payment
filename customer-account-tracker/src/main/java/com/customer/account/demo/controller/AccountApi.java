package com.customer.account.demo.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestHeader;

import com.customer.account.demo.model.AccountRequest;
import com.customer.account.demo.model.CustomResponse;
import com.customer.account.demo.model.CustomerResponse;
import com.customer.account.demo.model.PaymentConfirmRequest;
import com.customer.account.demo.model.PaymentConfirmResponse;

import static com.customer.account.demo.constant.ApiHeaderConstants.SURESH_CHANNEL;


public interface AccountApi {
    @ApiOperation(value = "Add Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CustomResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> addAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@Valid  AccountRequest request);
    
    @ApiOperation(value = "Get All Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CustomResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> getAllAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel);
    
    @ApiOperation(value = "Get Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CustomResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> getAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel, String customerId);
    
    @ApiOperation(value = "Update Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CustomResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> updateAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@Valid  AccountRequest request);
    
    @ApiOperation(value = "Delete Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CustomResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "External API Error")
    })
    public ResponseEntity<Object> deleteAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel, String customerId);
    
    
}
