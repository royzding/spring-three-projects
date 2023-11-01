package com.sample.microservices.common.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@Profile("flux-security")
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class FluxSecurityConfiguration {

	private static final String HEADER_SVC_KEY = "X-SVC-KEY";

	@Value("${auth.svcKey}")
	private String svcKey;
	
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

		return http.addFilterBefore(new WebFilter() {
			
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
				
			   	System.out.println("*****************@EnableWebFluxSecurity:SecurityFilterChain*******************");

			   	String svcHeader = exchange.getRequest().getHeaders().getFirst(svcKey);
			   	
			   	System.out.println("=====exchange.getRequest()========" + exchange.getRequest());
			   	System.out.println("=====svcHeader========" + svcHeader);
				
/*    	 
		        if(svcHeader == null || !svcHeader.equals(this.svcKey)) {
		        	throw new BadCredentialsException("The Service Key was not found!");
		        }
*/
				
				return chain.filter(exchange);
			}
			
		}, SecurityWebFiltersOrder.HTTP_HEADERS_WRITER)
				
				  .authorizeExchange(exchanges -> exchanges
				  .pathMatchers("/**").permitAll())
				  .csrf(csrf -> csrf.disable())
				  .build();
	}
	
/*	
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .pathMatchers("/admin")
            .hasAuthority("ROLE_ADMIN")
            .anyExchange()
            .authenticated()
            .and()
            .formLogin()
            .and()
            .csrf()
            .disable()
            .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
            .withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();

        return new MapReactiveUserDetailsService(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
*/
}