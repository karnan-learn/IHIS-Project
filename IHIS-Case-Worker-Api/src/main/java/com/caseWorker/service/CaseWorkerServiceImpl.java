package com.caseWorker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.caseWorker.binding.Card;
import com.caseWorker.binding.LoginForm;
import com.caseWorker.binding.Profile;
import com.caseWorker.exception.CaseWorkerNotFoundException;
import com.ihis.entity.CaseWorkersAcctEntity;
import com.ihis.repository.AppPlanRepository;
import com.ihis.repository.CaseWorkersAcctRepository;
import com.ihis.repository.CitizenAppsRepository;
import com.ihis.repository.EligibilityDtlsRepository;
import com.ihis.util.ReadMailBody;
import com.ihis.util.SendEmail;

@Service
public class CaseWorkerServiceImpl implements CaseWorkerService {

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
		CaseWorkersAcctEntity cwDtl = cwRepo.findByEmailAndPwd(loginForm.getEmail(),loginForm.getPwd());
//		if(cwDtl != null) {
//			return "Welcome to Ashok IT….";
//		}else {
//			return "Invalid Credentials";
//		}
		if(cwDtl == null) {
			throw new CaseWorkerNotFoundException("Invalid Credentials");
		}
		return "Welcome to Ashok IT….";
	}

	@Override
	public String forgetPassword(String email) {
		CaseWorkersAcctEntity cwDtl = cwRepo.findByEmail(email);
		if(cwDtl != null) {
			if( 
					util.send(cwDtl.getEmail(),
							new String[] {},
							new String[] {}, 
							"Account Password", 
							readMailBody.readMailBodyContent(recoveryPasswordEmailTemplate, cwDtl), 
							new Resource[] {})
					) {
				return "Success && Password Mailed";
			}else {
				return "Sending email is failed && again try !!";
			}
		}else {
			return "Invalid Email";
		}
	}

	@Override
	public String updateCaseWorkerProfile(Profile cwDtls) {
		CaseWorkersAcctEntity cwDtl = cwRepo.findByEmail(cwDtls.getEmail());
		BeanUtils.copyProperties(cwDtls,cwDtl);
		cwRepo.save(cwDtl);
		return "Case Worker Profile Sucessfully Updated";
	}

	@Override
	public Profile fetchCaseWorkerProfileInfo(String email) {
		CaseWorkersAcctEntity cwDtl = cwRepo.findByEmail(email);
		Profile profile = new Profile();
		BeanUtils.copyProperties(cwDtl, profile);
		return profile;
	}

	@Override
	public Card fetchDashboardCardData() {
		Card card= new Card();
		card.setPlansCount(appPlanRepo.count());
		card.setAppsCount(citizenAppRepo.count());
		card.setApprovedCitizensCount(elgDtlsRepo.getCitizensCountByPlanStatus("Approved"));
		card.setDeniedCitizesCount(elgDtlsRepo.getCitizensCountByPlanStatus("Denied"));
		return card;
	}
}
