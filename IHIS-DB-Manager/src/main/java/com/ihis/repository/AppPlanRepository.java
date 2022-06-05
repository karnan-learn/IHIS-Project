package com.ihis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihis.entity.AppPlanEntity;

@Repository
public interface AppPlanRepository extends JpaRepository<AppPlanEntity, Integer>{

//    @Query("select plan from AppPlanEntity plan where plan.activeSw = :active")
//    public List<AppPlanEntity> findAllByactivePlan(@Param("active") char active);
//
//    @Query("select plan from AppPlanEntity plan where plan.planId = :planId and plan.activeSw = :active")
//    public AppPlanEntity findByPlanIdAndactivePlan(@Param("planId") int planId, @Param("active") char active);
	public AppPlanEntity findByPlanId(int planId);
}
