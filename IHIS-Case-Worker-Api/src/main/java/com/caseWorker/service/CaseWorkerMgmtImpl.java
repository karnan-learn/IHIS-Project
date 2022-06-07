package com.caseWorker.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.caseWorker.binding.CaseWorkerAccountDtls;
import com.caseWorker.binding.LoginForm;
import com.ihis.entity.CaseWorkersAcctEntity;
import com.ihis.repository.AppPlanRepository;
import com.ihis.repository.CaseWorkersAcctRepository;
import com.ihis.repository.CitizenAppsRepository;
import com.ihis.repository.EligibilityDtlsRepository;
import com.ihis.util.ReadMailBody;
import com.ihis.util.SendEmail;

@Service
public class CaseWorkerMgmtImpl implements CaseWorkerMgmt {

	@Autowired
	private CaseWorkersAcctRepository cwRepo;
	
	@Autowired
	private AppPlanRepository appPlanRepo;
	
	@Autowired
	private CitizenAppsRepository citizenAppRepo;
	
	@Autowired
	private EligibilityDtlsRepository elgDtlsRepo;
	
	@Autowired
	private SendEmail util;
	
	@Autowired
	private ReadMailBody readMailBody;
	
	@Value(value =  "${recoveryPasswordEmailTemplate}")
	String recoveryPasswordEmailTemplate;
	
	@Override
	public String signIn(LoginForm loginForm) {
		CaseWorkersAcctEntity cwDtl = cwRepo.findByEmail(loginForm.getEmail());
		if(cwDtl != null) {
			if(cwDtl.getPwd().equals(loginForm.getPwd())){
				return "Welcome to Ashok IT….";
			}else {
				return "Invalid Password";
			}
		}else {
			return "Invalid Email";
		}
	}

	@Override
	public String forgetPassword(String email) {
		try {
			CaseWorkersAcctEntity cwDtl = cwRepo.findByEmail(email);
			System.out.println(cwDtl);
			if(cwDtl != null) {
				if( 
						 util.send(cwDtl.getEmail(),
								new String[] {},
								new String[] {}, 
								"Account Password", 
								readMailBody.readMailBodyContent(recoveryPasswordEmailTemplate, cwDtl), 
								new Resource[] {})
						) {
					return "Password mailed";
				}else {
					return "Sending email is failed && again try !!";
				}
			}else {
				return "Invalid Email";
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			return "Email must be not null";
		}
	}

	@Override
	public String updateProfile(int cwAcctId,CaseWorkerAccountDtls cwDtls) {
		Optional<CaseWorkersAcctEntity> cwDtl = cwRepo.findByAcctId(cwAcctId);
		if(cwDtl.isPresent()) {
			CaseWorkersAcctEntity caseWorker=cwDtl.get();
			BeanUtils.copyProperties(cwDtls,caseWorker);
			cwRepo.save(caseWorker);
			return "Case Worker Profile Sucessfully Updated";
		}else {
			return "User not found";
		}
	}

	@Override
	public long getTotalPlans() {
		return appPlanRepo.getTotalPlans();
	}

	@Override
	public long getTotalApplications() {
		return citizenAppRepo.getTotalApplications();
	}

	@Override
	public long getApprovedCitizensCount() {
		return elgDtlsRepo.getCitizensCountByPlanStatus("Approved");
	}

	@Override
	public long getDeniedCitizensCount() {
		return elgDtlsRepo.getCitizensCountByPlanStatus("Denied");
	}
}
