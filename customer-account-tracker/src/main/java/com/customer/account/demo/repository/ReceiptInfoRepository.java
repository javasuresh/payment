package com.customer.account.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.account.demo.entity.ReceiptInfo;

@Repository
public interface ReceiptInfoRepository extends JpaRepository<ReceiptInfo,String> {
    @Query("select max(r.orderID) from ReceiptInfo r")
    String findMaxOrderId();
}
