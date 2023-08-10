package com.sample.microservices.department.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.microservices.department.data.model.DepartmentEntity;
import com.sample.microservices.department.service.BaseTest;

class DepartmentRepositoryTest extends BaseTest {
	
	@Autowired
	private DepartmentRepository repo;
	
	@Test
	void test_findByName() {
		
		List<DepartmentEntity> list = repo.findByName("d-name");
		
		assertNotNull(list);
	}

	
	@Test
	void test_findByName2() {
		assertNotNull(Integer.valueOf(1));
	}
}
