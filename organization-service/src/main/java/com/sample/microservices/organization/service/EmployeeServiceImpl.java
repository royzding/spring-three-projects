package com.sample.microservices.organization.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.microservices.organization.model.EmployeeEntity;
import com.sample.microservices.organization.repository.EmployeeEntityRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeEntityRepository repository;
	
	EmployeeServiceImpl(EmployeeEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public EmployeeEntity getEmployeeById(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return this.repository.findAll();
	}
	

}
