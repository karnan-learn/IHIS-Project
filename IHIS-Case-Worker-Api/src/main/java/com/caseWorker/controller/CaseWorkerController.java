package com.caseWorker.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseWorker.binding.CaseWorkerAccountDtls;
import com.caseWorker.binding.LoginForm;
import com.caseWorker.service.CaseWorkerMgmt;

@RestController
@RequestMapping("/")
public class CaseWorkerController {
	
	@Autowired
	private CaseWorkerMgmt caService;
	
	@PostMapping("signin")
	public ResponseEntity<String> signIn(@RequestBody LoginForm loginForm){
		String status = caService.signIn(loginForm);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@GetMapping("forget-password")
	public ResponseEntity<String> forgetPassword(@PathParam(value = "email") String email){
		String status = caService.forgetPassword(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@PostMapping("update-profile/{cwAcctId}")
	public ResponseEntity<String> updateProfile(@PathVariable(value = "cwAcctId") int cwAcctId, @RequestBody CaseWorkerAccountDtls cwAcctDtls){
		String status = caService.updateProfile(cwAcctId,cwAcctDtls);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@GetMapping("get-total-plans")
	public ResponseEntity<Long> getTotalPlans(){
		return new ResponseEntity<Long>(caService.getTotalPlans(),HttpStatus.OK);
	}
	
	@GetMapping("get-total-applications")
	public ResponseEntity<Long> getTotalApplications(){
		return new ResponseEntity<Long>(caService.getTotalApplications(),HttpStatus.OK);
	}
	
	@GetMapping("get-total-approved-citizen-counts")
	public ResponseEntity<Long> getApprovedCitizensCount(){
		return new ResponseEntity<Long>(caService.getApprovedCitizensCount(),HttpStatus.OK);
	}
	
	@GetMapping("get-total-denied-citizen-counts")
	public ResponseEntity<Long> getDeniedCitizensCount(){
		return new ResponseEntity<Long>(caService.getDeniedCitizensCount(),HttpStatus.OK);
	}
}
