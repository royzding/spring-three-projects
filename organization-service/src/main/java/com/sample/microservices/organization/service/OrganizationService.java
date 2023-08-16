package com.sample.microservices.organization.service;

import java.util.List;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.organization.model.EmployeeEntity;

public interface OrganizationService {

	EmployeeEntity getEmployeeById(final Long id);
    
    List<EmployeeEntity> getAllEmployees();
    
    Employee createEmployee(Employee employee);
    
    void deleteEmployeeById(final Long id);

    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
    
    Manager createManager(Manager manager);
    
    void deleteManagerById(final Long id);    
   
}
