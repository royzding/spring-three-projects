package com.sample.microservices.organization.service;

import java.util.List;

import com.sample.microservices.organization.model.EmployeeEntity;

public interface EmployeeService {

	EmployeeEntity getEmployeeById(final Long id);
    
    List<EmployeeEntity> getAllEmployees();
    
}
