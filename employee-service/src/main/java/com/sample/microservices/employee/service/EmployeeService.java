package com.sample.microservices.employee.service;

import java.util.List;

import com.sample.microservices.employee.model.dto.Employee;
 
public interface EmployeeService
{
    List<Employee> getAllEmployees();
    
}