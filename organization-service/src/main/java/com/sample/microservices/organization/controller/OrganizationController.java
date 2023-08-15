package com.sample.microservices.organization.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.organization.model.EmployeeEntity;
import com.sample.microservices.organization.service.EmployeeService;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	private final EmployeeService employeeService;
	
	OrganizationController(EmployeeService employeeService) {
		this.employeeService = employeeService;		
	}

	@GetMapping("/all")
	public List<EmployeeEntity> getAllDepartments() {
		return this.employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeeEntity getEmployeeById(@PathVariable("id") Long id) {
		return this.employeeService.getEmployeeById(id);
	}
	
	
}
