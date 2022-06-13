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
@Table(name = "CITIZEN_CHILD_DTLS")
@NoArgsConstructor
@AllArgsConstructor
public class CitizenChildDtlsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CHILD_ID")
	private Integer childId;
	
	@Column(name="CASE_NUM")
	private Integer caseNum;
	
	@Column(name="CHILD_NAME")
	private String childName;
	
	@Column(name="CHILD_DOB")
	private LocalDate childDob;

	@Column(unique=true,name="CHILD_SSN")
	private String childSsn;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;

}
