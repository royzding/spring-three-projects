package com.sample.microservices.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.employee","com.sample.microservices.common"})
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/employee"),
			@Server(url = "http://localhost:8083/") 
		}, 
		info = @Info(title = "employee-service", 
		version = "v3", 
		description = "Employee-Service APIs", 
		license = @License(name = "Apache 2.0", url = "http://foo.bar"), 
		contact = @Contact(url = "http://employee-server.com", 
		name = "Roy", 
		email = "Roy@example.com"))
)
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
