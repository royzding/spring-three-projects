package com.sample.microservices.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.sample.microservices.gateway.filter.SecurityWebFilter;

@Configuration
//@Profile("flux-security")
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FluxSecurityConfiguration {

	@Value("${auth.svcKey}")
	private String svcKey;
	
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

		return http				
				  .addFilterBefore(new SecurityWebFilter(this.svcKey), SecurityWebFiltersOrder.HTTP_HEADERS_WRITER)				
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