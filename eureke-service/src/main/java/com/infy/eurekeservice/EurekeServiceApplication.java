package com.infy.eurekeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // This annotation is used to enable the Eureka server
public class EurekeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekeServiceApplication.class, args);
	}

}
