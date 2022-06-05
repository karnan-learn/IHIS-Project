package com.admin.api.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

	private String PlanName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private int categoryId;
}
