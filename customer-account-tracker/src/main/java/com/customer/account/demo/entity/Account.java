package com.customer.account.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "opening_balance")
    private BigDecimal openingBalance;
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    @Column(name="status")
    private Boolean status;
    
    private String branchName;
    private String branchAddress;
    
}
