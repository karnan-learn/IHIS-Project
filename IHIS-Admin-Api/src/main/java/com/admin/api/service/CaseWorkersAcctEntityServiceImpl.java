package com.admin.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.admin.api.binding.CaseWorkerAccountRegForm;
import com.admin.api.utils.PasswordUtils;
import com.ihis.entity.CaseWorkersAcctEntity;
import com.ihis.repository.CaseWorkersAcctRepository;
import com.ihis.util.ReadMailBody;
import com.ihis.util.SendEmail;

@Service
public class CaseWorkersAcctEntityServiceImpl implements CaseWorkersAcctEntityService {

	@Autowired
	private CaseWorkersAcctRepository cwrepo;
	
	@Autowired
	private SendEmail util;
	
	@Autowired
	private ReadMailBody readMailBody;
	
	@Value(value =  "${temporaryPasswordTemplate}")
	private String temporaryPasswordTemplate;
	
	@Override
	public String upsert(CaseWorkerAccountRegForm cw) {
		CaseWorkersAcctEntity cwEntity = new CaseWorkersAcctEntity();
		if(isEmailNotExist(cw.getEmail())) {
			
			BeanUtils.copyProperties(cw,cwEntity);
			cwEntity.setActiveSw('Y');
			cwEntity.setPwd(PasswordUtils.generateRandomPassword(12));
			CaseWorkersAcctEntity savedCW = cwrepo.save(cwEntity);
			
			boolean sent = util.send(savedCW.getEmail(),
					new String[] {},
					new String[] {}, 
					"Temporary Password", 
					readMailBody.readMailBodyContent(temporaryPasswordTemplate, savedCW), 
					new Resource[] {});
			System.out.println(sent);
			if(sent) {
				return "User Registration Successfull && Check Your Email for Temporary Password";
			}else {
				cwrepo.delete(savedCW);
				return "Sending Email Failed && again try";
			}
		}else {
			return "Email Alredy Exists !!!";
		}
	}

	@Override
	public boolean isEmailNotExist(String email) {
		CaseWorkersAcctEntity cwEntity = cwrepo.findByEmail(email);
		if(cwEntity!=null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<CaseWorkersAcctEntity> getAllCaseWorkers() {
		List<CaseWorkersAcctEntity> cw = cwrepo.findAll();
		return cw;
	}

	@Override
	public CaseWorkersAcctEntity getGetCaseWorker(int cwAcctId) {
		Optional<CaseWorkersAcctEntity> cw = cwrepo.findByAcctId(cwAcctId);
		if(cw.isPresent()) {
			return cw.get();
		}else {
			return null;
		}
	}

	@Override
	public String deleteCaseWorker(int cwAcctId) {
		Optional<CaseWorkersAcctEntity> findById = cwrepo.findByAcctId(cwAcctId);
		cwrepo.delete(findById.get());
		return "Deleted Sucess";
	}

	@Override
	public String CaseWorkerActiveSw(int cwAcctId) {
		Optional<CaseWorkersAcctEntity> findById = cwrepo.findByAcctId(cwAcctId);
		CaseWorkersAcctEntity plan = findById.get();
		if (findById.isPresent() && findById.get().getActiveSw()=='Y') {
			plan.setActiveSw('N');
			cwrepo.save(plan);
			return "Active Switch Disabled";
		}else {
			plan.setActiveSw('Y');
			cwrepo.save(plan);
			return "Active Switch Enabled";
		}
	}
}
