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

@Entity
@Data
@Table(name = "APP_PLANS")
@NoArgsConstructor
@AllArgsConstructor
public class AppPlanEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PLAN_ID")
	private int planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_START_DATE")
	private LocalDate planStDate;
	
	@Column(name="PLAN_END_DATE")
	private LocalDate planEnDate;
	
	@Column(name="CATEGORY_ID")
	private int categoryId;
	
	@Column(name="ACTIVE_SW")
	private char activeSw;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;

}
