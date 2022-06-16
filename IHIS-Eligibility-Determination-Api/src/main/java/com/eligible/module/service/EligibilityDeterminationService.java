package com.eligible.module.service;

import java.util.List;

import com.eligible.module.binding.EligibilityPlanDtls;

public interface EligibilityDeterminationService {
	public List<EligibilityPlanDtls> EligibilityDetermination(Integer caseNum);
}
