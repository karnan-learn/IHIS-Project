package com.caseWorker.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Profile {

	private String fullName;
	private String email;
	private long mobileNum;
	private char gender;
	private LocalDate dob;
	private long ssn;
}
