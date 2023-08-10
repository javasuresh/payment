package com.customer.account.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;
  
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "grand_father_name")
    private String grandFatherName;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "date_type")
    private String dateType;
    @Column(name = "gender")
    private String gender;
 
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "source_of_income")
    private String SourceOfIncome;
}

