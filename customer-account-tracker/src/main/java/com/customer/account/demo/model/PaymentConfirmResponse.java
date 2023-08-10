package com.customer.account.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmResponse {
    private String transactionId;
    private CustomerReciverDetails customerReciverDetails;
    private ReceiptData receiptData;
    private String paymentMethod;
    private LocalDateTime paymentDateTime;
    private String codeType;
}
