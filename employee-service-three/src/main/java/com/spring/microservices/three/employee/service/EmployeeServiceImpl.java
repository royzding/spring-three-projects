package com.spring.microservices.three.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microservices.three.employee.model.EmployeeEntity;
import com.spring.microservices.three.employee.repository.EmployeeEntityRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeEntityRepository employeeEntityRepo;

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		
		List<EmployeeEntity> entities = this.employeeEntityRepo.findAll();
				
		return entities;
	}
}