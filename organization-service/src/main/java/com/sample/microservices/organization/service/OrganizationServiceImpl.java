package com.sample.microservices.organization.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.organization.map.EmployeeMapper;
import com.sample.microservices.organization.map.ManagerMapper;
import com.sample.microservices.organization.model.EmployeeEntity;
import com.sample.microservices.organization.model.ManagerEntity;
import com.sample.microservices.organization.repository.EmployeeEntityRepository;
import com.sample.microservices.organization.repository.ManagerEntityRepository;

import jakarta.transaction.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final EmployeeMapper empMapper;
	private final EmployeeEntityRepository empRepository;
	
	private final ManagerMapper manMapper;    
	private final ManagerEntityRepository manRepository;
    
	OrganizationServiceImpl(EmployeeMapper empMapper, EmployeeEntityRepository empRepository, 
			ManagerMapper manMapper, ManagerEntityRepository manRepository) {
		this.empMapper = Mappers.getMapper(EmployeeMapper.class);
		this.empRepository = empRepository;
		this.manMapper = Mappers.getMapper(ManagerMapper.class);
		this.manRepository = manRepository;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return this.empMapper.entityToEmployee(this.empRepository.findById(id).get());
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.empMapper.entityToEmployee(this.empRepository.findAll());
	}
	
	@Override
	//@Transactional
	public Employee createEmployee(Employee employee) {
		EmployeeEntity entity = this.empMapper.employeeToEntity(employee);
		
		this.empRepository.save(entity);
		
		Manager manager = new Manager();
		manager.setId(null);
		manager.setName("man-06-18");
		manager.setSalary(1000.0);
		
		createManager(manager);
		
		int x = 1/0;
		
		return this.empMapper.entityToEmployee(entity);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		this.empRepository.deleteById(id);		
	}

	@Override
	public Manager getManagerById(final Long id) {
				
		return this.manMapper.entityToManager(this.manRepository.findById(id).get());
	}

	@Override
	@Cacheable("all-managers")
	public List<Manager> getAllManagers() {
		
		List<ManagerEntity> entities = this.manRepository.findAll();
		
		return this.manMapper.entityToManager(entities);
	}

	@Override
	public Manager createManager(Manager manager) {
		ManagerEntity entity = this.manMapper.managerToEntity(manager);
		
		this.manRepository.save(entity);
		
		return this.manMapper.entityToManager(entity);
	}

	@Override
	public void deleteManagerById(Long id) {
		this.manRepository.deleteById(id);		
	}

}
