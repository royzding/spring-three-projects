package com.sample.microservices.employee.service;

import java.util.List;

import com.sample.microservices.employee.dto.model.Employee;
 
public interface EmployeeService
{
    List<Employee> getAllEmployees();
    
}