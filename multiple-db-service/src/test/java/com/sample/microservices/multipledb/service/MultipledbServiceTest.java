package com.sample.microservices.multipledb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.multipledb.map.EmployeeMapper;
import com.sample.microservices.multipledb.map.ManagerMapper;
import com.sample.microservices.multipledb.model.first.EmployeeEntity;
import com.sample.microservices.multipledb.repository.first.EmployeeEntityRepository;
import com.sample.microservices.multipledb.repository.first.ManagerEntityRepository;
import com.sample.microservices.multipledb.repository.second.OrderEntityRepository;
import com.sample.microservices.multipledb.repository.second.UserEntityRepository;
import com.sample.microservices.multipledb.service.MultipledbService;
import com.sample.microservices.multipledb.service.MultipledbServiceImpl;

class MultipledbServiceTest extends BaseTest {
	
	@MockBean
	private EmployeeMapper empMapper;
	
	@MockBean
	private EmployeeEntityRepository empRepository;
	
	@MockBean
	private ManagerMapper manMapper;
	
	@MockBean
	private ManagerEntityRepository manRepository;
	
	@MockBean
	private UserEntityRepository userEntityRepository;
	
	@MockBean
	private OrderEntityRepository orderEntityRepository;	
	
	@MockBean
	private MultipledbService multipledbService;
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		multipledbService = new MultipledbServiceImpl(empMapper, empRepository, manMapper, manRepository, userEntityRepository, orderEntityRepository);
		
	}
	
	@Test
	void test_getEmployeeById() {
		
		EmployeeEntity eEntity = new EmployeeEntity();
		eEntity.setId(1L);
		eEntity.setName("employee_name");
		
		Mockito.when(this.empRepository.findById(1L)).thenReturn(Optional.ofNullable(eEntity));
		
		Employee employee = this.multipledbService.getEmployeeById(1L);
		
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
