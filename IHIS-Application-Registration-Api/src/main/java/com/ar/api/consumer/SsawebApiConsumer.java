package com.ar.api.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SSA-WEB-API-SERVICE")
public interface SsawebApiConsumer {
	@GetMapping("https://ssa-web-api.herokuapp.com/ssn/69787978")
	public ResponseEntity<String> getStateNameBasedOnSsn();
}
