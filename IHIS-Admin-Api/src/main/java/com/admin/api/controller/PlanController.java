package com.admin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.api.binding.Plan;
import com.admin.api.service.PlanEntityService;
import com.ihis.entity.AppPlanEntity;

@RestController
public class PlanController {

	@Autowired
	private PlanEntityService service;
	
	@PostMapping("/plan")
	public ResponseEntity<String> saveOrUpdate(@RequestBody Plan plan){
		String status = service.upsert(plan);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<AppPlanEntity>> getAllPlans( ){
		List<AppPlanEntity> allPlans = service.getAllPlans();
		return ResponseEntity.ok().body(allPlans);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<AppPlanEntity> getPlan(@PathVariable int planId){
		AppPlanEntity plan = service.getPlan(planId);
		if(plan==null) {
			return ResponseEntity.notFound().build();
		}else {	
			return ResponseEntity.ok().body(plan);
		}
	}
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable int planId){
		String status = service.deletePlan(planId);
		return ResponseEntity.ok().body(status);
	}
	
	@DeleteMapping("/plan/enable/{planId}")
	public ResponseEntity<String> PlanActiveOrDeactive(@PathVariable int planId){
		String status = service.PlanActiveOrDeactive(planId);
		return ResponseEntity.ok().body(status);
	}
}

