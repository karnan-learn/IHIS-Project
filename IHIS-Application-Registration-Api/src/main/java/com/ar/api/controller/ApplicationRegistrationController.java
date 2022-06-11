package com.ar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.api.binding.ApplicationForm;
import com.ar.api.binding.ApplicationProfile;
import com.ar.api.service.ApplicationRegistrationService;

@RestController
@RequestMapping("/")
public class ApplicationRegistrationController {

	@Autowired
	private ApplicationRegistrationService ars;
	
	@PostMapping("create-application")
	public ResponseEntity<String> createApplication(@RequestBody ApplicationForm applicationForm){
		return new ResponseEntity<String>(ars.upsert(applicationForm),HttpStatus.CREATED);
	}
	
	@GetMapping("get-all-applications")
	public ResponseEntity<List<ApplicationProfile>> getAllApplications(){
		return new ResponseEntity<>(ars.getApplicationDtls(),HttpStatus.OK);
	}
}
