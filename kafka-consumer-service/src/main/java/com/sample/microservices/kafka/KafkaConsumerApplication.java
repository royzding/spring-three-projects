package com.sample.microservices.kafka;

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

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.kafka.*","com.sample.microservices.common.*"})
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/consumer"),
			@Server(url = "http://localhost:8086/") 
		}, 
		info = @Info(title = "kafka-consumer-service", 
		version = "v3", 
		description = "Kafka-consumer-Service APIs", 
		license = @License(name = "Apache 2.0", url = "http://foo.bar"), 
		contact = @Contact(url = "http://kafka-consumer-service.com", 
		name = "Roy", 
		email = "Roy@example.com"))
)
@EnableCaching
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}