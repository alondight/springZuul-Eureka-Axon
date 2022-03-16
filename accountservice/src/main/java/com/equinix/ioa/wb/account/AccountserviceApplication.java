package com.equinix.ioa.wb.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class AccountserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}
}
