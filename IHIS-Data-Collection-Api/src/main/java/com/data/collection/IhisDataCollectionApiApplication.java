package com.data.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = {"com.ihis.repository"})
@EntityScan(basePackages = {"com.ihis.entity"})
public class IhisDataCollectionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IhisDataCollectionApiApplication.class, args);
	}

}
