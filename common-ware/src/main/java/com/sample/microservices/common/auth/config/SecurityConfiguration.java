package com.sample.microservices.common.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sample.microservices.common.auth.filter.HeaderSecurityFilter;
import com.sample.microservices.common.auth.filter.JwtAuthenticationEntryPoint;
import com.sample.microservices.common.auth.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("api-security")
public class SecurityConfiguration { //extends WebSecurityConfigurerAdapter {
	
	@Value("${auth.svcKey}")
	private String svcKey;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	System.out.println("*****************SecurityFilterChain*******************");
    	
        http.csrf().disable()
        .authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
                .anyRequest().permitAll()
        );
        
        //http.addFilterBefore(new HeaderSecurityFilter(svcKey), BasicAuthenticationFilter.class);
        
        return http.build();

    }
	
/*
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
    	System.out.println("$$$$$$$$$$$$$$$$$$$$$ get into svcKey==" + svcKey);
    	System.out.println("$$$$$$$$$$$$$$$$$$$$$ get into" + SecurityConfiguration.class);

		http
		.addFilterBefore(new HeaderSecurityFilter(svcKey), BasicAuthenticationFilter.class)
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/**").permitAll();
	}
*/
	
/*	
	private UserDetailsService userDetailsService;
	
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SecurityConfiguration(UserDetailsService userDetailsService,
                          JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAuthenticationFilter authenticationFilter){
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
        http.csrf().disable()
        .authorizeHttpRequests((authorize) ->
                //authorize.anyRequest().authenticated()
                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()

        ).exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint)
        ).sessionManagement( session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        
        http.addFilterBefore(new HeaderSecurityFilter(svcKey), BasicAuthenticationFilter.class);
        
        return http.build();

    }
     
	//  @Bean
	//  public UserDetailsService userDetailsService(){
	//      UserDetails ramesh = User.builder()
	//              .username("ramesh")
	//              .password(passwordEncoder().encode("ramesh"))
	//              .roles("USER")
	//              .build();
	//
	//      UserDetails admin = User.builder()
	//              .username("admin")
	//              .password(passwordEncoder().encode("admin"))
	//              .roles("ADMIN")
	//              .build();
	//      return new InMemoryUserDetailsManager(ramesh, admin);
	//  }}
	 
*/
	 
    
}