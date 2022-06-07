package com.admin.api.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.api.binding.CaseWorkerAccountRegForm;
import com.admin.api.service.CaseWorkersAcctEntityService;
import com.ihis.entity.CaseWorkersAcctEntity;


@RestController
@RequestMapping("/")
public class CaseWorkerAcctController {

	@Autowired
	private CaseWorkersAcctEntityService cwService;
	
	@PostMapping("create-account")
	public ResponseEntity<String> createAccount(@RequestBody CaseWorkerAccountRegForm cwForm){
		String status = cwService.upsert(cwForm);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("email-available-check")
	public ResponseEntity<String> emailAvailableCheck(@PathParam(value = "email") String email){
		if(cwService.isEmailNotExist(email)) {
			return new ResponseEntity<String>("Email is valid",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Email is already available",HttpStatus.OK);
		}
	}
	
	@GetMapping("get-all-case-workers")
	public ResponseEntity<List<CaseWorkersAcctEntity>> getAllCaseWorkers( ){
		List<CaseWorkersAcctEntity> cws = cwService.getAllCaseWorkers();
		return ResponseEntity.ok().body(cws);
	}
	
	@GetMapping("get-case-worker/{cwAcctId}")
	public ResponseEntity<CaseWorkersAcctEntity> getCaseWorker(@PathVariable("cwAcctId") int cwAcctId ){
		CaseWorkersAcctEntity cw = cwService.getGetCaseWorker(cwAcctId);
		return ResponseEntity.ok().body(cw);
	}
	
	@DeleteMapping("/case-worker/{cwAcctId}")
	public ResponseEntity<String> deleteCaseWorker(@PathVariable("cwAcctId") int cwAcctId){
		String status = cwService.deleteCaseWorker(cwAcctId);
		return ResponseEntity.ok().body(status);
	}
	
	@DeleteMapping("/case-worker/enable/{cwAcctId}")
	public ResponseEntity<String> CaseWorkerActiveSw(@PathVariable("cwAcctId") int cwAcctId){
		String status = cwService.CaseWorkerActiveSw(cwAcctId);
		return ResponseEntity.ok().body(status);
	}
}
