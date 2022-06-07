package com.ihis.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihis.entity.CitizenAppsEntity;

@Repository
public interface CitizenAppsRepository extends JpaRepository<CitizenAppsEntity, Id> {

	@Query("select count(citizenApps) from CitizenAppsEntity citizenApps")
	public long getTotalApplications();
}
