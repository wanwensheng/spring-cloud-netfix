package com.wan.userserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.wan.controller","com.wan.sourceloader"})
@SpringBootApplication
public class UserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceProviderApplication.class, args);
	}

}
