package com.caseWorker.service;

import com.caseWorker.binding.Card;
import com.caseWorker.binding.LoginForm;
import com.caseWorker.binding.Profile;

public interface CaseWorkerService {

	//Sign
	String signIn(LoginForm loginForm);
	
	//ForgetPassword
	String forgetPassword(String email);
	
	//Update Profile
	public Profile fetchCaseWorkerProfileInfo(String email);
	public String updateCaseWorkerProfile(Profile cwDtls);
	
	//DashBoard Functionalities
	public Card fetchDashboardCardData();
	//	public Map<String,Long> fetchDashboardCardData();
}
