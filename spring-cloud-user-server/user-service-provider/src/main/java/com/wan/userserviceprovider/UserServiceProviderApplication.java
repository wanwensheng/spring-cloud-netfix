package com.wan.userserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.wan.controller","com.wan.sourceloader","com.wan.config",
"com.wan.springcloud.clients","com.wan.userserviceprovider"})
@EnableFeignClients(basePackages = "com.wan.springcloud.clients")
@SpringBootApplication
public class UserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceProviderApplication.class, args);
	}

}
