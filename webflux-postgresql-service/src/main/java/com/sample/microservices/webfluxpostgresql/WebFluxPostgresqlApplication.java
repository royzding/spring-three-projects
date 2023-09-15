package com.sample.microservices.webfluxpostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.webfluxpostgresql","com.sample.microservices.common"})
@EnableDiscoveryClient
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/webflux-postgresql"),
			@Server(url = "http://localhost:8087/webflux-postgresql") 
		}, 
		info = @Info(title = "webflux-postgresql", 
		version = "${springdoc.open-api.version}", 
		description = "webflux-postgresql-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://webflux-postgresql-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class WebFluxPostgresqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxPostgresqlApplication.class, args);
    }
}

//https://dzone.com/articles/r2dbc-reactive-programming-with-spring-part-4

//-Dspring.profiles.active=local,flux-security

//swagger url
//http://localhost:8087/webflux-postgresql/