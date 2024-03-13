package com.sample.microservices.department.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.department.data.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
}