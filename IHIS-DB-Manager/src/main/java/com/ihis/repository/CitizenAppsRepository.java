package com.ihis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.entity.CitizenAppsEntity;
import com.ihis.entity.CitizenGraduationDtlsEntity;

@Repository
public interface CitizenAppsRepository extends JpaRepository<CitizenAppsEntity, Integer> {

//	@Query("select count(citizenApps) from CitizenAppsEntity citizenApps")
//	public long getTotalApplications();
	public CitizenAppsEntity findByCaseNum(Integer caseNum);
}
