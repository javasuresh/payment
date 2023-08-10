package com.customer.account.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Table(name = "receipt_info")
public class ReceiptInfo {
    @Id
    @Column(name = "order_id")
    private String orderID;
    @Column(name = "order_value")
    private BigDecimal orderValue;
    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage;
    @Column(name = "discount_value")
    private BigDecimal discountValue;
    @Column(name = "vat_percentage")
    private BigDecimal vatPercentage;
    @Column(name = "vat_value")
    private BigDecimal vatValue;
}
