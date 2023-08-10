package com.customer.account.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptData {

    private BigDecimal orderValue;
    private BigDecimal discountPercentage;
    private BigDecimal discountValue;
    private BigDecimal vatPercentage;
    private BigDecimal vatValue;
    private String orderID;
}
