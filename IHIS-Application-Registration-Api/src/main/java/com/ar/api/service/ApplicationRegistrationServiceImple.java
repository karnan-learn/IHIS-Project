package com.ar.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ar.api.binding.ApplicationForm;
import com.ar.api.binding.ApplicationProfile;
import com.ar.api.consumer.SsawebApiConsumer;
import com.ihis.entity.CitizenAppsEntity;
import com.ihis.repository.CitizenAppsRepository;

@Service
public class ApplicationRegistrationServiceImple implements ApplicationRegistrationService {

	@Autowired
	private CitizenAppsRepository citizenAppsRepository;
	
	@Value(value="${sshWebServiceApi}")
	private String sshWebServiceApi;
	
	@Override
	public String upsert(ApplicationForm applicationForm) {
		
		String response=null;
		
		CitizenAppsEntity ctAppEntity = new CitizenAppsEntity();
		BeanUtils.copyProperties(applicationForm,ctAppEntity);
		
		String url = sshWebServiceApi.toString() + "ssn/" + ctAppEntity.getSsn();
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> stateName=rt.getForEntity(url, String.class);

		if(stateName.getBody() != "Invalid SSN") {
			ctAppEntity.setStateName(stateName.getBody());
			ctAppEntity.setActiveSw('Y');
			citizenAppsRepository.save(ctAppEntity);
			response="Citizens Application Saved Sucessfully";
		}else {
			response="Invalid SSN";
		}
		
		return response;
	}

	@Override
	public List<ApplicationProfile> getApplicationDtls() {
		
		List<CitizenAppsEntity> citizenApplications = citizenAppsRepository.findAll();
		List<ApplicationProfile> applicationDtls = new ArrayList<>();
		
		if(citizenApplications == null) {
			applicationDtls= null;
		}else {
			for(CitizenAppsEntity ctApp : citizenApplications) {
				ApplicationProfile profile = new ApplicationProfile();
				BeanUtils.copyProperties(ctApp,profile);
				applicationDtls.add(profile);
			}
		}
		return applicationDtls;
	}

}
