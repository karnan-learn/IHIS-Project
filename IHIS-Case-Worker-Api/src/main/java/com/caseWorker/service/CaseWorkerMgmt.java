package com.caseWorker.service;

import com.caseWorker.binding.CaseWorkerAccountDtls;
import com.caseWorker.binding.LoginForm;

public interface CaseWorkerMgmt {

	//Sign
	String signIn(LoginForm loginForm);
	
	//ForgetPassword
	String forgetPassword(String email);
	
	//Update Profile
	public String updateProfile(int cwAcctId,CaseWorkerAccountDtls cwDtls);
	
	//DashBoard Functionalities
	public long getTotalPlans();
	public long getTotalApplications();
	public long getApprovedCitizensCount();
	public long getDeniedCitizensCount();
	
}
