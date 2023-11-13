package com.example.TestData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDataApplication.class, args);
	}

}

//  mvn package azure-webapp:deploy