package com.sample.microservices.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.department.data.model.DepartmentWZEntity;

@Repository
public interface DepartmentWZRepository extends JpaRepository<DepartmentWZEntity, Long>{

}

