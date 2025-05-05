package com.sample.microservices.hapifhir.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FhirServerConfig {
/*
    @Bean
    public ServletRegistrationBean<RestfulServer> fhirServletRegistration() {
        return new ServletRegistrationBean<>(new RestfulServer() {
            {
                setFhirContext(FhirContext.forR4());
                registerProvider(new PatientResourceProvider());
            }
        }, "/fhir/*");
    }

 */
}