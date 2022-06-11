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
	public CaseWorkersAcctEntity getCaseWorker(String email) {
		CaseWorkersAcctEntity cw = cwrepo.findByEmail(email);
		if(cw != null) {
			return cw;
		}else {
			return null;
		}
	}

	@Override
	public String deleteCaseWorker(int cwAcctId) {
		cwrepo.deleteById(cwAcctId);
		return "Deleted Sucess";
	}

	@Override
	public String CaseWorkerActiveSw(int cwAcctId) {
		Optional<CaseWorkersAcctEntity> findById = cwrepo.findById(cwAcctId);
		if (findById.isPresent() && findById.get().getActiveSw()=='Y') {
			findById.get().setActiveSw('N');
			cwrepo.save(findById.get());
			return "Active Switch Disabled";
		}else {
			findById.get().setActiveSw('Y');
			cwrepo.save(findById.get());
			return "Active Switch Enabled";
		}
	}
}
