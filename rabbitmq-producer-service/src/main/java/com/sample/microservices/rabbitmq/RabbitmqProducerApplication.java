package com.sample.microservices.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.rabbitmq.*","com.sample.microservices.common.*"})
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8091/producer"),
			@Server(url = "http://localhost:8091/") 
		}, 
		info = @Info(title = "rabbitmq-producer-service", 
		version = "v3", 
		description = "rabbitmq-Producer-Service APIs", 
		license = @License(name = "Apache 2.0", url = "http://foo.bar"), 
		contact = @Contact(url = "http://rabbitmq-producer-service.com", 
		name = "Roy", 
		email = "Roy@example.com"))
)
@EnableCaching

public class RabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

}
