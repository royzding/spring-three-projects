package com.sample.microservices.common.auth.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SvcKeyRequestInterceptor implements RequestInterceptor {
	
	private final String svcKey;
	
	public SvcKeyRequestInterceptor(String svcKey) { this.svcKey = svcKey;}

	@Override
	public void apply(RequestTemplate template) {
		template.header("X-SVC-Key", svcKey);
	}

}
