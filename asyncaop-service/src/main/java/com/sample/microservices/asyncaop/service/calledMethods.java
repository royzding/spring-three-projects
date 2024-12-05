package com.sample.microservices.asyncaop.service;

public interface calledMethods {
	
	default String A(final String msg) { return msg;}

}
