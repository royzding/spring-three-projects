package com.sample.microservices.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.common.model.UserDetailStore;

import lombok.Data;

@Component
public class AuthorizationPreFilter extends AbstractGatewayFilterFactory<AuthorizationPreFilter.Config> {
	
    final Logger logger =  LoggerFactory.getLogger(AuthorizationPreFilter.class);
    
    private static final String USER_DETAIL_HEADER = "X-USER-DETAIL";

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
                
				try {

                          	
	                request.mutate().header("secret", "123").build();
	                
					Map<String, Object> itemsMap;
					itemsMap = mapper.readValue(authorizationHeader, new TypeReference<Map<String, Object>>() {});

	                UserDetailStore userDetailStore = new UserDetailStore();
	                userDetailStore.setUsername((String)itemsMap.get("user_name"));
	                userDetailStore.setEmail((String)itemsMap.get("email"));
	                
	                String jsonUserDetailStore = mapper.writeValueAsString(userDetailStore);
	
	                System.out.println("===============jsonUserDetailStore=========" + jsonUserDetailStore);
	
	                request.mutate().header(USER_DETAIL_HEADER, Base64.getEncoder().encodeToString(jsonUserDetailStore.getBytes(StandardCharsets.UTF_8))).build();
               
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
           }

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    @Data
    public static class Config {
        private String groups;
    }
    
}