package com.ihis.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihis.entity.GraduationYearsEntity;

@Repository
public interface GraduationYearsRepository extends JpaRepository<GraduationYearsEntity, Integer>{

	@Query(value = "select year from GraduationYearsEntity")
	public List<Integer> getYears();

}
