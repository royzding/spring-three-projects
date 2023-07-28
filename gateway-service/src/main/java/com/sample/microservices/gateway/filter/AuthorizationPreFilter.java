package com.sample.microservices.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Component
public class AuthorizationPreFilter extends AbstractGatewayFilterFactory<AuthorizationPreFilter.Config> {
	
    final Logger logger =  LoggerFactory.getLogger(AuthorizationPreFilter.class);
    
    private static final String USER_INFO_HEADER = "X-USER-INFO";

    @Value("${auth.check-auth}")
	private boolean checkAuth;
	
    @Value("${auth.token.header}")
	private String authTokenHeader;
	
    @Autowired
    private ObjectMapper mapper;
    
    public AuthorizationPreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
    	
        return (exchange, chain) -> {
        	
            final ServerHttpRequest request = exchange.getRequest();
            final HttpHeaders headers = request.getHeaders();    
            
            if(checkAuth) {
            	
                if (!headers.containsKey(authTokenHeader)) {

                	String cause = "Authorization header not found!";
            		
            		logger.error("{} {} {}", cause, " [URI]:", request.getURI());
            		
            		return AuthorizationFilterUtil.unauthorizedAccess(exchange,cause);	        		
                }

                String groups = config.getGroups();
                
                String authorizationHeader = headers.get(authTokenHeader).get(0);

                if (!AuthorizationFilterUtil.isAuthorizedAccess(groups, authorizationHeader)) {

                	String cause = "Authorization header not found!";
            		
            		logger.error("{} {} {}", cause, " [URI]:", request.getURI());
            		
            		return AuthorizationFilterUtil.unauthorizedAccess(exchange,cause);	        		
                }
                          	
                request.mutate().header("secret", "123").build();
           }

            User user = new User();
            
            //userInfoStore.setUserName(headers.get(userInfoHeader).get(0));
            
            System.out.println("===============user=========" + user);

            request.mutate().header(USER_INFO_HEADER, user.getName()).build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    @Data
    public static class Config {
        private String groups;
    }
    
}