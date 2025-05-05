package com.sample.microservices.hapifhir;

import ca.uhn.fhir.context.FhirContext;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@OpenAPIDefinition
public class HapifhirApplication {

	public static void main(String[] args) {
		SpringApplication.run(HapifhirApplication.class, args);
	}

	@Bean
	public FhirContext fhirContext() {
		return FhirContext.forR4();
	}

}
