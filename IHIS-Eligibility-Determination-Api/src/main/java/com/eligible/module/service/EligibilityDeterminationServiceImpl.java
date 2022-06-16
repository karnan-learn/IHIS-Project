package com.eligible.module.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eligible.module.binding.EligibilityPlanDtls;
import com.ihis.entity.CitizenAppsEntity;
import com.ihis.entity.CitizenChildDtlsEntity;
import com.ihis.entity.CitizenGraduationDtlsEntity;
import com.ihis.entity.CitizenIncomeDtlsEntity;
import com.ihis.entity.CitizenPlansEntity;
import com.ihis.entity.EligibilityDtlsEntity;
import com.ihis.repository.CitizenAppsRepository;
import com.ihis.repository.CitizenChildDtlsRepository;
import com.ihis.repository.CitizenGraduationDtlsRepository;
import com.ihis.repository.CitizenIncomeDtlsRepository;
import com.ihis.repository.CitizenPlansRepository;
@Service
public class EligibilityDeterminationServiceImpl implements EligibilityDeterminationService {

	@Autowired
	private CitizenPlansRepository ctizenPlanRepo;
	@Autowired
	private CitizenIncomeDtlsRepository citizenIncDtlsRepo;
	@Autowired
	private CitizenGraduationDtlsRepository citizenGraduationDtlsRepo;
	@Autowired
	private CitizenChildDtlsRepository citizenChildDtlsRepo;
	@Autowired
	private CitizenAppsRepository citizenAppRepo;
	
	@Override
	public List<EligibilityPlanDtls> EligibilityDetermination(Integer caseNum) {
		
		CitizenPlansEntity planDtlsEntity= ctizenPlanRepo.findByCaseNum(caseNum);
		CitizenAppsEntity citizenDtls = citizenAppRepo.findByCaseNum(caseNum);
		CitizenIncomeDtlsEntity incomeDtls= citizenIncDtlsRepo.findByCaseNum(planDtlsEntity.getCaseNum());
		
		EligibilityDtlsEntity eligibilityPlanDtls = new EligibilityDtlsEntity();
		List<EligibilityPlanDtls> eligibilityPlanDtlsBinding = new ArrayList<>();
		
		eligibilityPlanDtls.setCaseNum(caseNum);
		if(incomeDtls.getSalaryIncome()<300) {
	
			eligibilityPlanDtls.setPlanName("SNAP");
			eligibilityPlanDtls.setPlanStatus("Approved");
			eligibilityPlanDtls.setStartDate(LocalDate.now());
			eligibilityPlanDtls.setEndDate(LocalDate.now().plusYears(1));
			eligibilityPlanDtls.setBenifitAmt(350.00);
			eligibilityPlanDtls.setDenialReason("N/A");
			
			EligibilityPlanDtls entiy = new EligibilityPlanDtls();
			BeanUtils.copyProperties(eligibilityPlanDtls, entiy);
			eligibilityPlanDtlsBinding.add(entiy);	
			
		}else if(incomeDtls.getSalaryIncome()<300 && isCheckChildAge(caseNum)) {
			
			eligibilityPlanDtls.setPlanName("CCAP");
			eligibilityPlanDtls.setPlanStatus("Approved");
			eligibilityPlanDtls.setStartDate(LocalDate.now());
			eligibilityPlanDtls.setEndDate(LocalDate.now().plusYears(1));
			eligibilityPlanDtls.setBenifitAmt(350.00);
			eligibilityPlanDtls.setDenialReason("N/A");
			
			EligibilityPlanDtls entiy = new EligibilityPlanDtls();
			BeanUtils.copyProperties(eligibilityPlanDtls, entiy);
			eligibilityPlanDtlsBinding.add(entiy);	
			
		}else if(incomeDtls.getSalaryIncome()<300 && incomeDtls.getPropertyIncome()<=200) {
			
			eligibilityPlanDtls.setPlanName("Medicaid");
			eligibilityPlanDtls.setPlanStatus("Approved");
			eligibilityPlanDtls.setStartDate(LocalDate.now());
			eligibilityPlanDtls.setEndDate(LocalDate.now().plusYears(1));
			eligibilityPlanDtls.setBenifitAmt(350.00);
			eligibilityPlanDtls.setDenialReason("N/A");
			
			EligibilityPlanDtls entiy = new EligibilityPlanDtls();
			BeanUtils.copyProperties(eligibilityPlanDtls, entiy);
			eligibilityPlanDtlsBinding.add(entiy);	
			
		}else if((LocalDate.now().getYear() - citizenDtls.getDob().getYear())>=65) {
			
			eligibilityPlanDtls.setPlanName("Medicare");
			eligibilityPlanDtls.setPlanStatus("Approved");
			eligibilityPlanDtls.setStartDate(LocalDate.now());
			eligibilityPlanDtls.setEndDate(LocalDate.now().plusYears(1));
			eligibilityPlanDtls.setBenifitAmt(350.00);
			eligibilityPlanDtls.setDenialReason("N/A");
			
			EligibilityPlanDtls entiy = new EligibilityPlanDtls();
			BeanUtils.copyProperties(eligibilityPlanDtls, entiy);
			eligibilityPlanDtlsBinding.add(entiy);	
			
		}else if(isCheckNJWpLAN(caseNum)) {
			
			eligibilityPlanDtls.setPlanName("NJW");
			eligibilityPlanDtls.setPlanStatus("Approved");
			eligibilityPlanDtls.setStartDate(LocalDate.now());
			eligibilityPlanDtls.setEndDate(LocalDate.now().plusYears(1));
			eligibilityPlanDtls.setBenifitAmt(350.00);
			eligibilityPlanDtls.setDenialReason("N/A");
			
			EligibilityPlanDtls entiy = new EligibilityPlanDtls();
			BeanUtils.copyProperties(eligibilityPlanDtls, entiy);
			eligibilityPlanDtlsBinding.add(entiy);	
			
		}
		return eligibilityPlanDtlsBinding;
	}
	
	
	private boolean isCheckChildAge(Integer caseNum) {
		List<CitizenChildDtlsEntity> dtls = citizenChildDtlsRepo.findByCaseNum(caseNum);
		for(CitizenChildDtlsEntity dtl:dtls) {
			if(LocalDate.now().getYear()-dtl.getChildDob().getYear()<16){
				return true;
			}
		}
		return false;
	}
	
	private boolean isCheckNJWpLAN(Integer caseNum) {
		CitizenPlansEntity planDtlsEntity= ctizenPlanRepo.findByCaseNum(caseNum);
		CitizenIncomeDtlsEntity incomeDtls= citizenIncDtlsRepo.findByCaseNum(planDtlsEntity.getCaseNum());
		CitizenGraduationDtlsEntity graduationtls = citizenGraduationDtlsRepo.findByCaseNum(caseNum);
		if( (graduationtls != null) && (incomeDtls == null )) {
			return true;
		}
		return false;
	}
	
}
