package com.ihis.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihis.entity.EligibilityDtlsEntity;

@Repository
public interface EligibilityDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Integer> {

	@Query("select count(eligibilityDtls) from EligibilityDtlsEntity eligibilityDtls where eligibilityDtls.planStatus = :planStatus")
	public long getCitizensCountByPlanStatus(@Param("planStatus") String planStatus);
	
}
