package com.sample.microservices.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.organization.model.EmployeeEntity;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long>{
	
	List<EmployeeEntity> findByName(String name);
	List<EmployeeEntity> findByNameIn(List<String> name);
	
}
