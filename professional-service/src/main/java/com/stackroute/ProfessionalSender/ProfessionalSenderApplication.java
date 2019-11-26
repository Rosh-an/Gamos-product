package com.stackroute.ProfessionalSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**to specify it as spring boot application*/
@SpringBootApplication
/** to enable eureka*/
@EnableEurekaClient
public class ProfessionalSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessionalSenderApplication.class, args);
	}

}
