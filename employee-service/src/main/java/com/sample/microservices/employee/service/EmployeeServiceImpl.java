package com.sample.microservices.employee.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.sample.microservices.employee.map.EmployeeMapper;
import com.sample.microservices.employee.model.dao.EmployeeEntity;
import com.sample.microservices.employee.model.dto.Employee;
import com.sample.microservices.employee.repository.EmployeeEntityRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeEntityRepository employeeRepository;
    
    private final EmployeeMapper employeeMapper;
    
    public EmployeeServiceImpl(final EmployeeEntityRepository employeeRepository, final EmployeeMapper employeeMapper) {
    	this.employeeRepository = employeeRepository;
    	this.employeeMapper = employeeMapper;
    }

	@Override
	public List<Employee> getAllEmployees() {
		
		List<EmployeeEntity> entities = this.employeeRepository.findAll();
				
		return this.employeeMapper.entityToEmployee(entities);
	}
}