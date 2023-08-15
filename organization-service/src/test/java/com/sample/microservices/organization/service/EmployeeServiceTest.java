package com.sample.microservices.organization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sample.microservices.organization.model.EmployeeEntity;
import com.sample.microservices.organization.repository.EmployeeEntityRepository;

class EmployeeServiceTest extends BaseTest {
	
	@MockBean
	private EmployeeEntityRepository repository;
	
	@MockBean
	private EmployeeService employeeService;
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		employeeService = new EmployeeServiceImpl(repository);
		
	}
	
	@Test
	void test_getEmployeeById() {
		
		EmployeeEntity eEntity = new EmployeeEntity();
		eEntity.setId(1L);
		eEntity.setName("employee_name");
		
		Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(eEntity));
		
		EmployeeEntity employee = this.employeeService.getEmployeeById(1L);
		
		assertEquals(1L, employee.getId());
		
	}

/*
	@Test
	void test_getAllDepartments() {
		
		DepartmentEntity dEntity = new DepartmentEntity();
		dEntity.setId(1L);
		dEntity.setName("depart_name");
		
		Mockito.when(this.repository.findAll()).thenReturn(entities);
		
		List<Department> departments = this.departmentService.getAllDepartments();
		
		assertEquals(2, departments.size());
		
	}
*/
}
