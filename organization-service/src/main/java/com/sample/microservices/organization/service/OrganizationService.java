package com.sample.microservices.organization.service;

import java.util.List;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;

public interface OrganizationService {

	Employee getEmployeeById(final Long id);
    
    List<Employee> getAllEmployees();
    
    Employee createEmployee(Employee employee);
    
    void deleteEmployeeById(final Long id);

    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
    
    Manager createManager(Manager manager);
    
    void deleteManagerById(final Long id);    
   
}
