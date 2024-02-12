package com.sample.microservices.department.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.sample.microservices.common.auth.config.SvcKeyRequestInterceptor;

import feign.Logger;
import feign.RequestInterceptor;


public class EmployeeApiFeignClientConfig {
	
	@Value("${feign.client.config.employee-service.defaultRequestHeaders.X-SVC-KEY")
	private String svcKey;
	
	@Bean
	Logger.Level feignLoggerLevel() {return Logger.Level.FULL;}
	
	@Bean
	public RequestInterceptor svcKeyInterceptor() {
		return new SvcKeyRequestInterceptor(svcKey);
	}

}
