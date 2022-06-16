package com.ihis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.entity.CitizenChildDtlsEntity;

@Repository
public interface CitizenChildDtlsRepository extends JpaRepository<CitizenChildDtlsEntity, Integer> {

	public List<CitizenChildDtlsEntity> findByCaseNum(Integer caseNum);
}
