package com.eligible.module.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class EligibilityPlanDtls {
	
	private Integer caseNum;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endaDate;
	private String beniftAmount;
	private String denialReason;
}
