package com.data.collection.binding;

import java.util.List;

import lombok.Data;
@Data
public class SummaryData{
	private CitizenIncomeDtls incomeDtls;
	private List<CitizenGraduationDtls> eduDtls;
	private List<CitizenChildDtls> kidDtls;
}
