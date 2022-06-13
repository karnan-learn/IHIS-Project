package com.data.collection.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.ihis.entity.GraduationYearsEntity;
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
	public Map<Integer, String> getAllPlan() {
		List<AppPlanEntity> allPlans = planRepo.findAll();
		Map<Integer, String> appPlans = new HashMap<>();
		for(AppPlanEntity plan:allPlans) {
			appPlans.put(plan.getPlanId(), plan.getPlanName());
		}
		return appPlans;
	}

	@Override
	public String createCitizenPlan(CitizenPlanDtls planDtls) {
		CitizenPlansEntity citizenPlansEntity = new CitizenPlansEntity();
		BeanUtils.copyProperties(planDtls, citizenPlansEntity);
		ctizenPlanRepo.save(citizenPlansEntity);
		return "Citizen Plan Saved Successfully";
	}

	@Override
	public String createCitizenIncomeDtls(CitizenIncomeDtls incomeDtls) {
		CitizenIncomeDtlsEntity citizenIncomeDtlsEntity = new CitizenIncomeDtlsEntity();
		BeanUtils.copyProperties(incomeDtls,citizenIncomeDtlsEntity);
		citizenIncDtlsRepo.save(citizenIncomeDtlsEntity);
		return "Citizen Income Details Saved Successfully";
	}

	@Override
	public List<Integer> getAllGraduationYear() {
		List<GraduationYearsEntity> years=graduationYearsRepo.findAll();
		List<Integer> graduationYears = new ArrayList<>();
		for(GraduationYearsEntity year:years) {
			graduationYears.add(year.getYear());
		}
		return graduationYears;
	}

	@Override
	public String createCitizenGraduationDtls(CitizenGraduationDtls graduationDtls) {
		CitizenGraduationDtlsEntity citizenGraduationDtlsEntity = new CitizenGraduationDtlsEntity();
		BeanUtils.copyProperties(graduationDtls,citizenGraduationDtlsEntity);
		citizenGraduationDtlsRepo.save(citizenGraduationDtlsEntity);
		return "Citizen Graduation Details Saved Successfully";
	}

	@Override
	public String createCitizenChildsDtls(CitizenChildDtls childDtls) {
		CitizenChildDtlsEntity childDtlsEntity = new CitizenChildDtlsEntity();
		BeanUtils.copyProperties(childDtls,childDtlsEntity);
		citizenChildDtlsRepo.save(childDtlsEntity);
		return "Citizen Child Details Saved Successfully";
	}

	@Override
	public SummaryData getSummaryData(Integer CaseNumber) {
		SummaryData summaryData = new SummaryData();
		summaryData.setIncomeDtls(getCitizenIncomeDtls(CaseNumber));
		summaryData.setEduDtls(getCitizenGraduationDtls(CaseNumber));
		summaryData.setKidDtls(getCitizenChildDtls(CaseNumber));
		return summaryData;
	}
	
	private CitizenIncomeDtls getCitizenIncomeDtls(Integer CaseNumber) {
		CitizenIncomeDtlsEntity incomeDtls= citizenIncDtlsRepo.findById(CaseNumber).get();
		CitizenIncomeDtls citizenIncomeDtls = new CitizenIncomeDtls();
		BeanUtils.copyProperties(incomeDtls,citizenIncomeDtls);
		return citizenIncomeDtls;
	}
	
	private List<CitizenGraduationDtls> getCitizenGraduationDtls(Integer CaseNumber){
		List<CitizenGraduationDtlsEntity> dtls =  citizenGraduationDtlsRepo.findAll();
		List<CitizenGraduationDtls> graduationDtls = new ArrayList<>();
		
		for(CitizenGraduationDtlsEntity dtl:dtls) {
			CitizenGraduationDtls result=null;
			BeanUtils.copyProperties(dtl,result);
			graduationDtls.add(result);
		}
		return graduationDtls;
	}
	
	private List<CitizenChildDtls> getCitizenChildDtls(Integer CaseNumber){
		List<CitizenChildDtlsEntity> dtls = citizenChildDtlsRepo.findAll();
		List<CitizenChildDtls> childDtls = new ArrayList<>();
		
		for(CitizenChildDtlsEntity dtl:dtls) {
			CitizenChildDtls result=null;
			BeanUtils.copyProperties(dtl,result);
			childDtls.add(result);
		}
		return childDtls;
	}
}
