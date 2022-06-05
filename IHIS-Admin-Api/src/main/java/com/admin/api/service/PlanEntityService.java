package com.admin.api.service;

import java.util.List;

import com.admin.api.binding.Plan;
import com.ihis.entity.AppPlanEntity;



public interface PlanEntityService {
	
	public String upsert(Plan plan);
	public List<AppPlanEntity> getAllPlans();
	public AppPlanEntity getPlan(int planId);
	public String deletePlan(int planId);
	public String PlanActiveOrDeactive(int planId);
	
}
