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

@Data
@Entity
@Table(name = "CITIZEN_APPS")
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CASE_NUM")
	private int caseNum;
	
	@Column(name="FULLNAME")
	private String fullName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE_NUM")
	private long mobileNum;
	
	@Column(name="GENDER")
	private char gender;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(unique=true,name="SSN")
	private long ssn;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="ACTIVE_SW")
	private Character activeSw;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
}
