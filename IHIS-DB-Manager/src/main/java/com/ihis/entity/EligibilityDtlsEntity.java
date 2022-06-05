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
@Table(name = "ELIG_DTLS")
public class EligibilityDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ELIG_ID")
	private int eligId;
	
	@Column(name="CASE_NUM")
	private int caseNum;
	
	@Column(name="PLAN_NAME")
	private int planName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
		
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	@Column(name="BENEFIT_AMT")
	private double benifitAmt;
	
	@Column(name="DENIAL_REASON")
	private String DenialReason;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
}
