package com.wan.springcloudhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
public class SpringCloudHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixDashboardApplication.class, args);
	}

}
