package com.customer.account.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.customer.account.demo.constant.ValidationConstants;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmRequest {

    @NotEmpty(message = ValidationConstants.CUSTOMER_VALIDATIONS)
    @Size(min=11,max=11, message = ValidationConstants.CUSTOMER_VALIDATIONS )
    @Pattern(regexp = "[0-9]+",message = ValidationConstants.CUSTOMER_VALIDATIONS)
    private String customerId;

    @NotEmpty(message = "This field is required")
    private String customerName;

    @NotEmpty(message = ValidationConstants.MOBILENO_VALIDATION)
    @Size(min=4,max=15, message = ValidationConstants.MOBILENO_VALIDATION)
    @Pattern(regexp = "[0-9]+",message= ValidationConstants.MOBILENO_VALIDATION)
    private String customerPhNo;

    @NotEmpty(message = ValidationConstants.TYPE_VALIDATION)
    @Pattern(regexp = "[a-zA-Z]+",message=ValidationConstants.TYPE_VALIDATION)
    private String customerType;

    @NotEmpty(message = ValidationConstants.MERCHANT_VALIDATION)
    @Pattern(regexp = "[0-9]+",message = ValidationConstants.MERCHANT_VALIDATION)
    private String merchantId;

    @NotEmpty(message = "This field is required")
    private String merchantName;

    @NotEmpty(message = ValidationConstants.TYPE_VALIDATION)
    @Pattern(regexp = "[a-zA-Z]+",message=ValidationConstants.TYPE_VALIDATION)
    private String merchantType;

    @NotEmpty(message = ValidationConstants.MOBILENO_VALIDATION)
    @Size(min=4,max=15, message = ValidationConstants.MOBILENO_VALIDATION)
    @Pattern(regexp = "[0-9]+",message= ValidationConstants.MOBILENO_VALIDATION)
    private String merchantPhNo;
    private String merchantLocation;


  
    @NotNull(message = "Enter the correct amount")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2,message = "Enter the correct amount")
    private BigDecimal amount;

    @NotEmpty(message = ValidationConstants.TYPE_VALIDATION)
    @Pattern(regexp = "[a-zA-Z]+",message=ValidationConstants.TYPE_VALIDATION)
    private String currency;

    @NotEmpty(message = "This field is required")
    private String paymentMethod;

    @NotEmpty(message = "This field is required")
    private String codeType;
    private String desc;
}
