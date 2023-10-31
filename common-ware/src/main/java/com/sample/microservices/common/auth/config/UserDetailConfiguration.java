package com.sample.microservices.common.auth.config;

import java.util.Arrays;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.sample.microservices.common.auth.filter.UserDetailFilter;
import com.sample.microservices.common.model.UserDetailStore;

@Configuration
@Profile("user-detail")
public class UserDetailConfiguration { 

	@Bean
	public UserDetailFilter userDetailFilter() {
		return new UserDetailFilter();
	}
	
	@Bean(name = "userDetailStore")
	@Scope(scopeName = "prototype")
	public UserDetailStore userDetailStore() {
		return new UserDetailStore();
	}	

	@Bean
	public FilterRegistrationBean<UserDetailFilter> tenantFilterRegistrationBean() {
		FilterRegistrationBean<UserDetailFilter> resultBean = new FilterRegistrationBean<>();
		resultBean.setFilter(this.userDetailFilter());
		resultBean.setUrlPatterns(Arrays.asList("/*"));
		resultBean.setName("User Detail Store Filter");
		resultBean.setOrder(1);
		return resultBean;		
	}
	
	@Bean(destroyMethod = "destroy")
	public ThreadLocalTargetSource threadLocalTenantStore() {
		ThreadLocalTargetSource resultSource = new ThreadLocalTargetSource();
		resultSource.setTargetBeanName("userDetailStore");
		return resultSource;
	}
	
	@Primary
	@Bean(name = "proxiedThreadLocalTargetSource")
	public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
		ProxyFactoryBean resultBean = new ProxyFactoryBean();
		resultBean.setTargetSource(threadLocalTargetSource);
		return resultBean;		
	}
   
}