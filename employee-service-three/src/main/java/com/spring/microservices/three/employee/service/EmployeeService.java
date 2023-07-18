package com.spring.microservices.three.employee.service;

import java.util.List;

import com.spring.microservices.three.employee.model.EmployeeEntity;
 
public interface EmployeeService
{
    List<EmployeeEntity> getAllEmployees();
    
}