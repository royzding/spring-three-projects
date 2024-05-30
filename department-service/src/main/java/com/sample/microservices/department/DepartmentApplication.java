package com.sample.microservices.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.department","com.sample.microservices.common"})
//@EnableRedisRepositories(basePackages = "com.sample.microservices.redis.repository")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/department"),
			@Server(url = "http://localhost:8084/dept") 
		}, 
		info = @Info(title = "department-service", 
		version = "v3", 
		description = "Department-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://department-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
@EntityScan(basePackages = {
		"com.sample.microservices.common.model.dao",
		"com.sample.microservices.department.data.model"
})
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}
}


//http://localhost:8082/department-service/v3/swagger-ui.html