package com.stackroute.graphcomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GraphComApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphComApplication.class, args);
	}

}
