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
import com.ihis.entity.CitizenAppsEntity;
import com.ihis.repository.CitizenAppsRepository;

@Service
public class ApplicationRegistrationServiceImple implements ApplicationRegistrationService {

	@Autowired
	private CitizenAppsRepository citizenAppsRepository;
	
	@Value(value="${sshWebServiceApi}")
	private String sshWebServiceApi;
	
	@Value(value="${stateOfCitizen}")
	private String stateOfCitizen;
	
	@Override
	public String upsert(ApplicationForm applicationForm) {
		
		CitizenAppsEntity ctAppEntity = new CitizenAppsEntity();
		BeanUtils.copyProperties(applicationForm,ctAppEntity);
		
		String stateNameGettingUrl = sshWebServiceApi.toString();
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> stateName=rt.getForEntity(stateNameGettingUrl, String.class,ctAppEntity.getSsn());

		if(stateOfCitizen.equals(stateName.getBody())) {
			ctAppEntity.setStateName(stateName.getBody());
			ctAppEntity.setActiveSw('Y');
			citizenAppsRepository.save(ctAppEntity);
			return "Citizens Application Saved Sucessfully";
		}
		return "Citizen Not Belogs to New Jersy";
	}

	@Override
	public List<ApplicationProfile> viewApplications() {
		
		List<CitizenAppsEntity> citizenApplications = citizenAppsRepository.findAll();
		List<ApplicationProfile> applicationDtls = new ArrayList<>();
		
		if(citizenApplications == null) {
			applicationDtls= null;
		}else {
			ApplicationProfile profile = new ApplicationProfile();
			for(CitizenAppsEntity ctApp : citizenApplications) {
				BeanUtils.copyProperties(ctApp,profile);
				applicationDtls.add(profile);
			}
		}
		return applicationDtls;
	}

}
