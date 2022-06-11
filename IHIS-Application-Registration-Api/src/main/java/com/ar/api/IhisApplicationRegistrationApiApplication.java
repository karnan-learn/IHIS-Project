package com.ar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = {"com.ihis.repository"})
@EntityScan(basePackages = {"com.ihis.entity"})
public class IhisApplicationRegistrationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IhisApplicationRegistrationApiApplication.class, args);
	}

}
