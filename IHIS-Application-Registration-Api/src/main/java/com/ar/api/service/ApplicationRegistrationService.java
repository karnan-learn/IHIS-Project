package com.ar.api.service;

import java.util.List;

import com.ar.api.binding.ApplicationForm;
import com.ar.api.binding.ApplicationProfile;

public interface ApplicationRegistrationService {

	public String upsert(ApplicationForm applicationForm);
	public List<ApplicationProfile> getApplicationDtls();
	
}
