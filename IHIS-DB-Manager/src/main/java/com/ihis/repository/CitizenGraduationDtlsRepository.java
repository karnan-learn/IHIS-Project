package com.ihis.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.entity.CitizenGraduationDtlsEntity;
import com.ihis.entity.CitizenIncomeDtlsEntity;

@Repository
public interface CitizenGraduationDtlsRepository extends JpaRepository<CitizenGraduationDtlsEntity, Integer> {

	public CitizenGraduationDtlsEntity findByCaseNum(Integer caseNum);
}
