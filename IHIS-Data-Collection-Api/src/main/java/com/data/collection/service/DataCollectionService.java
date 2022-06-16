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
	public Map<Integer, String> getAllPlanNames();
	public String savePlanSelection(CitizenPlanDtls planDtls);
	
	//Income Details
	public String saveCitizenIncomeDtls(CitizenIncomeDtls incomeDtls);
	
	//Graduation
	public List<Integer> getAllGraduationYear();
	public String saveCitizenGraduationDtls(CitizenGraduationDtls graduationDtls);
	
	//Citizen
	public String saveCitizenChildsDtls(CitizenChildDtls childDtls);
	
	//Summary
	public SummaryData getSummaryData(Integer CaseNumber);
}
