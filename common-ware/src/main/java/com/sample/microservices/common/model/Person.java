package com.sample.microservices.common.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Person {
	
	private Long id;
	
	@JsonProperty("FIRST_NAME")
	private String firstName;
	
	
	@JsonProperty("Last_NAME")
	private String lastName;
	private String email;
	
	private Address addr;
	
	private Map<String, String> attrMap;

}
