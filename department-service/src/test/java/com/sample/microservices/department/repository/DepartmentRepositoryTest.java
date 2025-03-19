package com.sample.microservices.department.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.microservices.common.model.dao.DepartmentEntity;
import com.sample.microservices.department.service.BaseTest;
import org.springframework.test.annotation.Rollback;

class DepartmentRepositoryTest extends BaseTest {
	
	@Autowired
	private DepartmentRepository repo;
	
	@Test
	void test_findByName() {
		
		List<DepartmentEntity> list = repo.findByName("d-name");
		
		assertNotNull(list);
	}

	
	@Test
	void test_findById() {

		DepartmentEntity dEntity = this.repo.findById(1L).get();

		//Verify
		System.out.println(dEntity);
		Assertions.assertThat(dEntity.getId()).isEqualTo(1L);
	}

	@Test
	void test_findAll() {

		List<DepartmentEntity> dEntities = this.repo.findAll();

		//Verify
		System.out.println(dEntities);
		Assertions.assertThat(dEntities.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Save Department Test")
	@Order(1)
	@Rollback(value = false)
	public void saveDepartmentTest(){

		DepartmentEntity dEntity = new DepartmentEntity();
		dEntity.setId(1L);
		dEntity.setName("depart_name");

		repo.save(dEntity);

		//Verify
		System.out.println(dEntity);
		Assertions.assertThat(dEntity.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Update Department Test")
	@Order(2)
	@Rollback(value = false)
	public void updateDepartmentTest(){

		DepartmentEntity dEntity = this.repo.findById(1L).get();
		dEntity.setName("depart_name");

		repo.save(dEntity);

		//Verify
		System.out.println(dEntity);
		Assertions.assertThat(dEntity.getName()).isEqualTo("depart_name");
	}

}
