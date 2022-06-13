package com.data.collection.service;

import java.util.List;
import java.util.Map;

import com.data.collection.binding.CitizenGraduationDtls;
import com.data.collection.binding.CitizenIncomeDtls;
import com.data.collection.binding.CitizenChildDtls;
import com.data.collection.binding.CitizenPlanDtls;
import com.data.collection.binding.SummaryData;

public interface DataCollectionService {
	//Plan
	public Map<Integer, String> getAllPlan();
	public String createCitizenPlan(CitizenPlanDtls planDtls);
	//Income Details
	public String createCitizenIncomeDtls(CitizenIncomeDtls incomeDtls);
	//Graduation
	public List<Integer> getAllGraduationYear();
	public String createCitizenGraduationDtls(CitizenGraduationDtls graduationDtls);
	//Citizen
	public String createCitizenChildsDtls(CitizenChildDtls childDtls);
	//Summary
	public SummaryData getSummaryData(Integer CaseNumber);
}
