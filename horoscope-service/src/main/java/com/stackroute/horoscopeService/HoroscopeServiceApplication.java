package com.stackroute.horoscopeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HoroscopeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoroscopeServiceApplication.class, args);
	}

}
