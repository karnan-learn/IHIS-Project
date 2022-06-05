package com.ihis.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CO_TRIGGERS")
public class CorrespondenceTriggersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRG_ID")
	private int trgId;
	
	@Column(name="CASE_NUM")
	private int caseNum;
	
	@Column(name="TRG_STATUS")
	private String trgStatus;
	
	@Column(name="NOTICE")
	private Blob notice;
}
