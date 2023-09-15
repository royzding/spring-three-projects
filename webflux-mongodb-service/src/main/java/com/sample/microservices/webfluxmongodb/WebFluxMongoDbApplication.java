package com.sample.microservices.webfluxmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.webfluxmongodb","com.sample.microservices.common"})
@EnableDiscoveryClient
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/webflux-mongodb"),
			@Server(url = "http://localhost:8085/webflux-mongodb") 
		}, 
		info = @Info(title = "webflux-mongodb-service", 
		version = "${springdoc.open-api.version}", 
		description = "webflux-mongodb-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://webflux-mongodb-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class WebFluxMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxMongoDbApplication.class, args);
    }
}

//-Dspring.profiles.active=local,flux-security

//swagger url
//http://localhost:8085/webflux-mongodb/v3/swagger-ui.html  