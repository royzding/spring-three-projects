package com.sample.microservices.webfluxoracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.webfluxoracle","com.sample.microservices.common"})
@EnableDiscoveryClient
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/webflux-oracle"),
			@Server(url = "http://localhost:8089/webflux-oracle") 
		}, 
		info = @Info(title = "webflux-oracle", 
		version = "${springdoc.open-api.version}", 
		description = "webflux-oracle-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://webflux-oracle-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class WebFluxOracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxOracleApplication.class, args);
    }
}

//https://dzone.com/articles/r2dbc-reactive-programming-with-spring-part-4

//-Dspring.profiles.active=local,flux-security

//swagger url
//http://localhost:8089/webflux-oracle/