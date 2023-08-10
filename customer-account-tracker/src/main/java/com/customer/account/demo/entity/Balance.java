package com.customer.account.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "balance")
public class Balance {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "customer_type")
    private String customerType;
    @Column(name = "last_updated")
    private LocalDateTime timeStamp;
    @Column(name = "balance")
    private BigDecimal balance;
}
