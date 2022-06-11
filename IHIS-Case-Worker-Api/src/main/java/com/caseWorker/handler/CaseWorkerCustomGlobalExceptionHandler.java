package com.caseWorker.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.caseWorker.exception.CaseWorkerNotFoundException;

@RestControllerAdvice
public class CaseWorkerCustomGlobalExceptionHandler {

	/**
	 * If exception raised one is CaseWorkerNotFoundException
	 * then execute below code
	 */
	@ExceptionHandler(CaseWorkerNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(
			CaseWorkerNotFoundException e) 
	{
		
		return new ResponseEntity<String>(
				e.getMessage(), 
				HttpStatus.INTERNAL_SERVER_ERROR); //500
	}
}
