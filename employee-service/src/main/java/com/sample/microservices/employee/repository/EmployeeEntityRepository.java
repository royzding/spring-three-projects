package com.sample.microservices.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.microservices.employee.dao.model.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {

}
