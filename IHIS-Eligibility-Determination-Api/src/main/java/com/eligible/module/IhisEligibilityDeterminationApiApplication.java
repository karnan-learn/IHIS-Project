package com.eligible.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.ihis.repository")
@EntityScan(basePackages = {"com.ihis.entity","com.ihis.util"})
public class IhisEligibilityDeterminationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IhisEligibilityDeterminationApiApplication.class, args);
	}

}
