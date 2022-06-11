package com.caseWorker.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseWorker.binding.Card;
import com.caseWorker.binding.LoginForm;
import com.caseWorker.binding.Profile;
import com.caseWorker.exception.CaseWorkerNotFoundException;
import com.caseWorker.service.CaseWorkerService;

@RestController
@RequestMapping("/")
public class CaseWorkerController {
	
	@Autowired
	private CaseWorkerService caService;
	
	@PostMapping("signin")
	public ResponseEntity<String> signIn(@RequestBody LoginForm loginForm){
//		String status = caService.signIn(loginForm);
//		return new ResponseEntity<String>(status,HttpStatus.OK);
		ResponseEntity<String> response = null;
		try {
			String status = caService.signIn(loginForm);
			response = new ResponseEntity<String>(status,HttpStatus.OK);
		}catch(CaseWorkerNotFoundException e){
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	@GetMapping("forget-password")
	public ResponseEntity<String> forgetPassword(@PathParam(value = "email") String email){
		String status = caService.forgetPassword(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@GetMapping("get-case-worker-profile")
	public ResponseEntity<Profile> fetchCaseWorkerProfileInfo(@PathParam(value = "email") String email){
		Profile profile = caService.fetchCaseWorkerProfileInfo(email);
		return new ResponseEntity<Profile>(profile,HttpStatus.OK);
	}
	
	@PostMapping("update-profile")
	public ResponseEntity<String> updateCaseWorkerProfile( @RequestBody Profile cwAcctDtls){
		String status = caService.updateCaseWorkerProfile(cwAcctDtls);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@GetMapping("get-dashboard-card-data")
	public ResponseEntity<Card> fetchDashboardCardData(){
		return new ResponseEntity<Card>(caService.fetchDashboardCardData(),HttpStatus.OK);
	}
}
