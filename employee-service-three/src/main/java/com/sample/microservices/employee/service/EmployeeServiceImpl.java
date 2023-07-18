package com.sample.microservices.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservices.employee.model.EmployeeEntity;
import com.sample.microservices.employee.repository.EmployeeEntityRepository;

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