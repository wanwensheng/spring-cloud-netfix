package com.wan.orderserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.wan"})
@SpringBootApplication
public class OrderServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceProviderApplication.class, args);
	}

}
