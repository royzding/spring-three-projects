package com.spring.microservices.three.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.microservices.three.employee.model.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {

}
