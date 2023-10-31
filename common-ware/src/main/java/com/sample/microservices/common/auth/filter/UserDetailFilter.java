package com.sample.microservices.common.auth.filter;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.common.model.UserDetailStore;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserDetailFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailFilter.class);
	
	private static final String USER_DETAIL_HEADER_NAME = "X-USER-DETAIL";

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private UserDetailStore userDetailStore;
	
	@Override
    public void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
    	
    	String userDetailHeader = request.getHeader(USER_DETAIL_HEADER_NAME);
    	
    	try {
    		
            if(userDetailHeader != null ) {

            	UserDetailStore userDetailStore = mapper.readValue(Base64.getDecoder().decode(userDetailHeader), UserDetailStore.class);
            	this.userDetailStore.copyData(userDetailStore);
            	LOGGER.info("Logged in User Detail {}", this.userDetailStore);
            }

            filterChain.doFilter(request, response);
			
		} finally {
			this.userDetailStore.clear();
		} 
    }
   
}