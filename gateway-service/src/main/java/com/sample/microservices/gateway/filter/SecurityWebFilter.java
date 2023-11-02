package com.sample.microservices.gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class SecurityWebFilter implements WebFilter {
		
	private static final String HEADER_GATEWAY_KEY = "X-GW-KEY";

	private String svcKey;
	
	public SecurityWebFilter(String svcKey) {
		this.svcKey = svcKey;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		log.info("*****************@EnableWebFluxSecurity:SecurityWebFilter*******************");

	   	String svcHeader = exchange.getRequest().getHeaders().getFirst(HEADER_GATEWAY_KEY);
	   	
	   	System.out.println("=====exchange.getRequest()========" + exchange.getRequest());
	   	System.out.println("=====svcHeader========" + svcHeader);
		    	 
        if(svcHeader == null || !svcHeader.equals(this.svcKey)) {
        	throw new BadCredentialsException("The Service Key was not found!");
        }

		HttpHeaders responseHeaders = exchange.getResponse().getHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "*");
		responseHeaders.add("Access-Control-Request-Method", "*");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Request-Headers", "*");
		responseHeaders.add("Access-Control-Allow-Credentials", "true");
		responseHeaders.add("Access-Control-Max-Age", "2400");
		

		return chain.filter(exchange);
	}
    
}