package com.sample.microservices.employee.model.dto;

import lombok.Data;

@Data
public class Employee {
	
	private static final long serialVersionUID = 2L;
	
	private long id;	  
	private String name;
	private Integer depId;	    
	private Integer managerId;	    
	private Double salary;
	
}
