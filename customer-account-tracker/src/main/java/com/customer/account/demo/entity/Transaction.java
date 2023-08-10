package com.customer.account.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    private String transactionId;
    private String debitCustomerId;
    private String creditCustomerId;
    private String status;
    private LocalDateTime initiationTime;
    private LocalDateTime lastUpdateTime;
    private String transactionType;
    private String reasonType;
    private BigDecimal amount;
    private BigDecimal debitPartyCharge;
    private BigDecimal debitPartyCommission;
    private BigDecimal creditPartyCharge;
    private BigDecimal creditPartyCommission;
    private String failureDescription;
    private String debitParty;
    private String debitPartyType;
    private String creditParty;
    private String creditPartyType;
    private BigDecimal costPrincipalCommission;
    private BigDecimal costResidualCommission;
    private BigDecimal revenueServiceFee;
}
