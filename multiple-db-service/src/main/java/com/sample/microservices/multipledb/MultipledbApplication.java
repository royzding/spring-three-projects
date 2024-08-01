package com.sample.microservices.multipledb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.sample.microservices.common.service.HolidayDateServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@ComponentScan(basePackages= {"com.sample.microservices.multipledb.*","com.sample.microservices.common.*"},
				excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = HolidayDateServiceImpl.class))
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8085/") 
		}, 
		info = @Info(title = "organization-service", 
		version = "v3", 
		description = "organization-Service APIs", 
		license = @License(name = "Apache 2.0", url = "http://foo.bar"), 
		contact = @Contact(url = "http://organization-server.com", 
		name = "Roy", 
		email = "Roy@example.com"))
)
/*
@EntityScan(basePackages = {
		"com.sample.microservices.common.model.dao",
		"com.sample.microservices.employee.model.dao"
})
*/
public class MultipledbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipledbApplication.class, args);
	}

}
