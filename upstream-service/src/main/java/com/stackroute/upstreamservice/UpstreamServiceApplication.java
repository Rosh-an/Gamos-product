package com.stackroute.upstreamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import java.time.Instant;

@SpringBootApplication
@EnableDiscoveryClient

public class UpstreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpstreamServiceApplication.class, args);
//		System.out.println(Instant.now().toEpochMilli());
	}
}
