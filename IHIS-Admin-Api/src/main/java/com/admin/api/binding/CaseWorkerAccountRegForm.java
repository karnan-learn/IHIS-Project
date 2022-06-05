package com.admin.api.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseWorkerAccountRegForm {

	private String fullName;
	private String email;
	private long mobileNum;
	private char gender;
	private LocalDate dob;
	private long ssn;
}
