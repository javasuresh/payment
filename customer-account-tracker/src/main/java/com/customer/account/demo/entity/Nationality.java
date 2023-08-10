package com.customer.account.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Table(name = "nationality")
public class Nationality {

	@Id
	@Column(name = "nationality_code")
	private String nationalityCode;

	@Column(name = "nationality_name")
	private String nationalityName;

}
