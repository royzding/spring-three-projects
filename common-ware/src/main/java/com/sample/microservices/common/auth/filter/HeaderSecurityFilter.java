package com.sample.microservices.common.auth.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderSecurityFilter extends OncePerRequestFilter {
	
	private static final String HEADER_SVC_KEY = "X-SVC-KEY";

	private String svcKey;
	
	public HeaderSecurityFilter(String svcKey) {
		this.svcKey = svcKey;
	}
 
	@Override
    public void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
    	
    	String svcHeader = request.getHeader(HEADER_SVC_KEY);
    	
    	System.out.println("====svcKey=========" + svcKey);
    	System.out.println("=============" + request.getRequestURI());
    	System.out.println("=====svcHeader========" + svcHeader);
    	 
        if(svcHeader == null || !svcHeader.equals(this.svcKey)) {
        	throw new BadCredentialsException("The Service Key was not found!");
        }

        filterChain.doFilter(request, response);

    }
}