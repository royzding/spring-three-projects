package com.sample.microservices.employee.service;

import java.util.List;

import com.sample.microservices.employee.model.EmployeeEntity;
 
public interface EmployeeService
{
    List<EmployeeEntity> getAllEmployees();
    
}