package com.didispace.truebineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbineStream
public class TruebineAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruebineAmqpApplication.class, args);
	}
}
