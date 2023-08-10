package com.customer.account.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountRequest {
    
    private String userId;
    private String userType;
    private BigDecimal openingBalance;
    private Boolean status;
    private LocalDateTime lastUpdate;
    private String branchName;
    private String branchAddress;
    
}
