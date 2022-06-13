package com.data.collection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.collection.binding.CitizenChildDtls;
import com.data.collection.binding.CitizenGraduationDtls;
import com.data.collection.binding.CitizenIncomeDtls;
import com.data.collection.binding.CitizenPlanDtls;
import com.data.collection.binding.SummaryData;
import com.data.collection.service.DataCollectionService;

@RestController
@RequestMapping("/")
public class DataCollectionController {
	@Autowired
	private DataCollectionService dcService;
	
	@PostMapping("create-plan")
	public ResponseEntity<String> createPlan(@RequestBody CitizenPlanDtls citizenPlanDtls){
		String status = dcService.createCitizenPlan(citizenPlanDtls);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@PostMapping("create-income-detail")
	public ResponseEntity<String> createIncomeDtls(@RequestBody CitizenIncomeDtls citizenIncomeDtls){
		String status = dcService.createCitizenIncomeDtls(citizenIncomeDtls);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@PostMapping("create-graduation-detail")
	public ResponseEntity<String> createGraduationDtls(@RequestBody CitizenGraduationDtls citizenGraduationDtls){
		String status = dcService.createCitizenGraduationDtls(citizenGraduationDtls);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@PostMapping("create-child-details")
	public ResponseEntity<String> createChildDtls(@RequestBody CitizenChildDtls citizenChildDtls){
		String status = dcService.createCitizenChildsDtls(citizenChildDtls);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("get-summary-data/{caseNum}")
	public ResponseEntity<SummaryData> getSummaryData(@PathVariable("caseNum") Integer caseNum){
		SummaryData summarData = dcService.getSummaryData(caseNum);
		return new ResponseEntity<>(summarData,HttpStatus.OK);
	}
}
