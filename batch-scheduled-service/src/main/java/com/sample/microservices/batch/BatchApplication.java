package com.sample.microservices.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.batch","com.sample.microservices.common"})
@EnableDiscoveryClient
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/batch"),
			@Server(url = "http://localhost:8087/batch") 
		}, 
		info = @Info(title = "batch-scheduled-service", 
		version = "${springdoc.open-api.version}", 
		description = "batch-scheduled-service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://batch-scheduled-service.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)

@EnableBatchProcessing
@EnableRetry
@EnableAsync
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}
}
