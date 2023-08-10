package com.customer.account.demo.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Builder
public class CustomerResponse {
    
    private String customerId;
    private String mobileNo;
    private String firstName;
    private String lastName;

    private String fatherName;
    private String grandFatherName;
    private String dateOfBirth;
    private String dateType;
    private String gender;
 
    private String companyName;
   
}

