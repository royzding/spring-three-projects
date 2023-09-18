package com.sample.microservices.webfluxoracle.service;

import com.sample.microservices.webfluxoracle.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface EmployeeService
{
    Mono<Employee> findByEmpId(Long id);

    Flux<Employee> findByName(String name);
    
    Flux<Employee> findAllEmp();

    Mono<Employee> createEmp(Employee e);
 
    Mono<Employee> updateEmp(Employee e);
 
    Mono<Void> deleteEmp(Long id);
    
}