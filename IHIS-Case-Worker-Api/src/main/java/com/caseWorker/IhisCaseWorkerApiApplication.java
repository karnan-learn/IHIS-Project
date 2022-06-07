package com.caseWorker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ihis.repository")
@EntityScan(basePackages = {"com.ihis.entity","com.ihis.util"})
public class IhisCaseWorkerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IhisCaseWorkerApiApplication.class, args);
	}

}
