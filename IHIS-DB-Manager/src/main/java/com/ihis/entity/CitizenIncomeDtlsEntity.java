package com.ihis.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CITIZEN_INCOME_DTLS")
public class CitizenIncomeDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INCOME_ID")
	private Integer incomeId;
	
	@Column(name="CASE_NUM")
	private Integer caseNum;
	
	@Column(name="SALARY_INCOME")
	private double salaryIncome;
	
	@Column(name="RENT_INCOME")
	private double rentIncome;
	
	@Column(name="PROPERTY_INCOME")
	private double propertyIncome;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	
	
}
