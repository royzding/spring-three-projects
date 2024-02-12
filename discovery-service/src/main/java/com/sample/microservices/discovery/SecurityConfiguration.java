package com.sample.microservices.discovery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration { 
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	System.out.println("*****************@EnableWebSecurity:SecurityFilterChain*******************");
    	
        http.headers()
        .contentSecurityPolicy("script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self'; connect-src 'self'; default-src 'self' frame-ancestors 'none';" );
        http.csrf().disable();
        
        return http.build();

    }
	 
    
}