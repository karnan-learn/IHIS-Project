package com.data.collection.binding;

import java.util.List;

import lombok.Data;
@Data
public class SummaryData{
	
	private CitizenPlanDtls planDtls;
	private CitizenIncomeDtls incomeDtls;
	private CitizenGraduationDtls graduationDtls;
	private CitizenChildDtls childDtls;
}
