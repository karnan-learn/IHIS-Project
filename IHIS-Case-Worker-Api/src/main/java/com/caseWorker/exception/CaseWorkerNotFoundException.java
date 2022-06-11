package com.caseWorker.exception;

public class CaseWorkerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaseWorkerNotFoundException() {
		super();
	}
	
	public CaseWorkerNotFoundException(String message) {
		super(message);
	}
}
