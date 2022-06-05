package com.ihis.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihis.entity.AppPlanEntity;
import com.ihis.entity.CaseWorkersAcctEntity;


@Repository
public interface CaseWorkersAcctRepository extends JpaRepository<CaseWorkersAcctEntity, Id> {

	public CaseWorkersAcctEntity findByEmail(String email);
	public Optional<CaseWorkersAcctEntity> findByAcctId(int acctId);
	
	
}
