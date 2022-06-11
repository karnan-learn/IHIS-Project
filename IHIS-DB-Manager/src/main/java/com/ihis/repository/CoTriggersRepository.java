package com.ihis.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihis.entity.CorrespondenceTriggersEntity;

@Repository
public interface CoTriggersRepository extends JpaRepository<CorrespondenceTriggersEntity, Integer> {

}
