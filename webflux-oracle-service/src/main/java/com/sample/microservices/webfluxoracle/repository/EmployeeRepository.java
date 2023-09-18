package com.sample.microservices.webfluxoracle.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.webfluxoracle.model.Employee;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {
	Flux<Employee> findByName(String name);
}