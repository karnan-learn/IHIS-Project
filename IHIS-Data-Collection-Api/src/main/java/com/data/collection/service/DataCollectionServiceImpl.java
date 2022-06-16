package com.data.collection.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.collection.binding.Child;
import com.data.collection.binding.CitizenChildDtls;
import com.data.collection.binding.CitizenGraduationDtls;
import com.data.collection.binding.CitizenIncomeDtls;
import com.data.collection.binding.CitizenPlanDtls;
import com.data.collection.binding.SummaryData;
import com.ihis.entity.AppPlanEntity;
import com.ihis.entity.CitizenChildDtlsEntity;
import com.ihis.entity.CitizenGraduationDtlsEntity;
import com.ihis.entity.CitizenIncomeDtlsEntity;
import com.ihis.entity.CitizenPlansEntity;
import com.ihis.repository.AppPlanRepository;
import com.ihis.repository.CitizenChildDtlsRepository;
import com.ihis.repository.CitizenGraduationDtlsRepository;
import com.ihis.repository.CitizenIncomeDtlsRepository;
import com.ihis.repository.CitizenPlansRepository;
import com.ihis.repository.GraduationYearsRepository;
@Service
public class DataCollectionServiceImpl implements DataCollectionService {

	@Autowired
	private AppPlanRepository planRepo;
	@Autowired
	private CitizenPlansRepository ctizenPlanRepo;
	@Autowired
	private CitizenIncomeDtlsRepository citizenIncDtlsRepo;
	@Autowired
	private GraduationYearsRepository graduationYearsRepo;
	@Autowired
	private CitizenGraduationDtlsRepository citizenGraduationDtlsRepo;
	@Autowired
	private CitizenChildDtlsRepository citizenChildDtlsRepo;
	
	@Override
	public Map<Integer, String> getAllPlanNames() {
		List<AppPlanEntity> allPlans = planRepo.findAll();
		Map<Integer, String> appPlans = new HashMap<>();
		for(AppPlanEntity plan:allPlans) {
//			appPlans.put(plan.getPlanId(), plan.getPlanName());
		}
		return appPlans;
	}

	@Override
	public String savePlanSelection(CitizenPlanDtls planDtls) {
		CitizenPlansEntity citizenPlansEntity = new CitizenPlansEntity();
		BeanUtils.copyProperties(planDtls, citizenPlansEntity);
		ctizenPlanRepo.save(citizenPlansEntity);
		return "Citizen Plan Saved Successfully";
	}

	@Override
	public String saveCitizenIncomeDtls(CitizenIncomeDtls incomeDtls) {
		CitizenIncomeDtlsEntity citizenIncomeDtlsEntity = new CitizenIncomeDtlsEntity();
		BeanUtils.copyProperties(incomeDtls,citizenIncomeDtlsEntity);
		citizenIncDtlsRepo.save(citizenIncomeDtlsEntity);
		return "Citizen Income Details Saved Successfully";
	}

	@Override
	public List<Integer> getAllGraduationYear() {
		return graduationYearsRepo.getYears();
	}

	@Override
	public String saveCitizenGraduationDtls(CitizenGraduationDtls graduationDtls) {
		CitizenGraduationDtlsEntity citizenGraduationDtlsEntity = new CitizenGraduationDtlsEntity();
		BeanUtils.copyProperties(graduationDtls,citizenGraduationDtlsEntity);
		citizenGraduationDtlsRepo.save(citizenGraduationDtlsEntity);
		return "Citizen Graduation Details Saved Successfully";
	}

	@Override
	public String saveCitizenChildsDtls(CitizenChildDtls childDtls) {
		List<Child> childsList = childDtls.getChilds();
		childsList.forEach(child->{
			CitizenChildDtlsEntity childDtlsEntity = new CitizenChildDtlsEntity();
			childDtlsEntity.setCaseNum(childDtls.getCaseNum());
			BeanUtils.copyProperties(child,childDtlsEntity);
			citizenChildDtlsRepo.save(childDtlsEntity);
		});
		return "Citizen Child Details Saved Successfully";
	}

	@Override
	public SummaryData getSummaryData(Integer caseNumber) {
		SummaryData summaryData = new SummaryData();
		summaryData.setPlanDtls(getCitizenPlanDtls(caseNumber));
		summaryData.setIncomeDtls(getCitizenIncomeDtls(caseNumber));
		summaryData.setGraduationDtls(getCitizenGraduationDtls(caseNumber));
		summaryData.setChildDtls(getCitizenChildDtls(caseNumber));
		return summaryData;
	}
	private CitizenPlanDtls getCitizenPlanDtls(Integer caseNumber) {
		CitizenPlansEntity planDtls= ctizenPlanRepo.findByCaseNum(caseNumber);
		CitizenPlanDtls citizenPlanDtls = new CitizenPlanDtls();
		BeanUtils.copyProperties(planDtls,citizenPlanDtls);
		return citizenPlanDtls;
	}
	
	private CitizenIncomeDtls getCitizenIncomeDtls(Integer caseNumber) {
		CitizenIncomeDtlsEntity incomeDtls= citizenIncDtlsRepo.findByCaseNum(caseNumber);
		CitizenIncomeDtls citizenIncomeDtls = new CitizenIncomeDtls();
		BeanUtils.copyProperties(incomeDtls,citizenIncomeDtls);
		return citizenIncomeDtls;
	}
	
	private CitizenGraduationDtls getCitizenGraduationDtls(Integer caseNumber){
		CitizenGraduationDtlsEntity dtls =  citizenGraduationDtlsRepo.findByCaseNum(caseNumber);
		CitizenGraduationDtls graduationDtls = new CitizenGraduationDtls();
		BeanUtils.copyProperties(dtls,graduationDtls);
		return null;
	}
	
	private CitizenChildDtls getCitizenChildDtls(Integer caseNumber){
		List<CitizenChildDtlsEntity> dtls = citizenChildDtlsRepo.findByCaseNum(caseNumber);
		List<Child> childDtlsList = new ArrayList<>();
		dtls.forEach(childEntiy->{
			Child child = new Child();
			 BeanUtils.copyProperties(childEntiy,child);
			 childDtlsList.add(child);
		});
		CitizenChildDtls childDtlsBinding = new CitizenChildDtls();
		childDtlsBinding.setCaseNum(caseNumber);
		childDtlsBinding.setChilds(childDtlsList);
		return childDtlsBinding;
	}
}
