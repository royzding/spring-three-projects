package com.sample.microservices.employee.department;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.sample.microservices.common.auth.config.SvcKeyRequestInterceptor;

import feign.Logger;
import feign.RequestInterceptor;


public class DepartmentApiFeignClientConfig {
	
	@Value("${feign.client.config.department-service.defaultRequestHeaders.X-SVC-KEY}")
	private String svcKey;
	
	@Bean
	Logger.Level feignLoggerLevel() {return Logger.Level.FULL;}
	
	@Bean
	public RequestInterceptor svcKeyInterceptor() {
		return new SvcKeyRequestInterceptor(svcKey);
	}

}
