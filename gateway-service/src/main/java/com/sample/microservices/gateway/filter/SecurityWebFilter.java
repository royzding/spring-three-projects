package com.sample.microservices.gateway.filter;

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
        	throw new BadCredentialsException("The Service Key was not found from gateway service!");
        }

		return chain.filter(exchange);
	}
    
}