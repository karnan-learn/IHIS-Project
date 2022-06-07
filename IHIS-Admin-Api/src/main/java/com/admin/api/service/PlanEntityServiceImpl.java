package com.admin.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.binding.Plan;
import com.ihis.entity.AppPlanEntity;
import com.ihis.repository.AppPlanRepository;

@Service
public class PlanEntityServiceImpl implements PlanEntityService{

	@Autowired
	private AppPlanRepository repo;
	
	@Override
	public String upsert(Plan plan) {
		System.out.println(plan);
		AppPlanEntity appPlanEntity =  new AppPlanEntity();
		
		appPlanEntity.setPlanName(plan.getPlanName());
		
		appPlanEntity.setPlanStDate(plan.getPlanStartDate());
		
		appPlanEntity.setPlanEnDate(plan.getPlanEndDate());
		
		appPlanEntity.setCategoryId(plan.getCategoryId());
		
		appPlanEntity.setActiveSw('Y');
		
		repo.save(appPlanEntity);
		
		return "SUCCESS";
	}

	@Override
	public List<AppPlanEntity> getAllPlans() {
		List<AppPlanEntity> plans = repo.findAll();
		System.out.println(plans);
		return plans;
	}

	@Override
	public AppPlanEntity getPlan(int planId) {
		Optional<AppPlanEntity> plan = repo.findById(planId);
		if(plan.isPresent()) {
			return plan.get();
		}else {
			return null;
		}
	}

	@Override
	public String deletePlan(int planId) {
		repo.deleteById(planId);
		return "Deleted Sucess";
	}

	@Override
	public String PlanActiveSw(int planId) {
		
		Optional<AppPlanEntity> findById = repo.findById(planId);
		AppPlanEntity plan = findById.get();
		if (findById.isPresent() && findById.get().getActiveSw()=='Y') {
			plan.setActiveSw('N');
			repo.save(plan);
			return "Active Switch Disabled";
		}else {
			plan.setActiveSw('Y');
			repo.save(plan);
			return "Active Switch Enabled";
		}
		
	}

}
