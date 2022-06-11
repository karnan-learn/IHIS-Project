package com.admin.api.service;

import java.util.List;

import com.admin.api.binding.CaseWorkerAccountRegForm;
import com.ihis.entity.CaseWorkersAcctEntity;

public interface CaseWorkersAcctEntityService {

	
	public String  upsert(CaseWorkerAccountRegForm user);
	public boolean isEmailNotExist(String email);
	
	public List<CaseWorkersAcctEntity> getAllCaseWorkers();
	public CaseWorkersAcctEntity getCaseWorker(String email);
	public String deleteCaseWorker(int cwAcctId);
	public String CaseWorkerActiveSw(int cwAcctId);
}
