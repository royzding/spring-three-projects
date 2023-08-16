package com.sample.microservices.organization.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.organization.model.EmployeeEntity;
import com.sample.microservices.organization.model.ManagerEntity;
import com.sample.microservices.organization.service.OrganizationService;

import jakarta.validation.Valid;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	private final OrganizationService organizationService;
	
	OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;		
	}

	@GetMapping("/employee/all")
	public List<Employee> getAllEmployees() {
		return this.organizationService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return this.organizationService.getEmployeeById(id);
	}
	
	@PostMapping("/employee/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {

		return this.organizationService.createEmployee(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {

		this.organizationService.deleteEmployeeById(id);
	}	
	
	@GetMapping("/manager/all")
	public List<Manager> getAllManagers() {
		return this.organizationService.getAllManagers();
	}
	
	@GetMapping("/manager/{id}")
	public Manager getManagerById(@PathVariable("id") Long id) {
		return this.organizationService.getManagerById(id);
	}
	
	@PostMapping("/manager/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Manager createManager(@Valid @RequestBody Manager manager) {

		return this.organizationService.createManager(manager);
	}
	
	@DeleteMapping("/manager/{id}")
	public void deleteManager(@PathVariable("id") Long id) {

		this.organizationService.deleteManagerById(id);
	}	
}
