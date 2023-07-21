package com.sample.microservices.employee.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.employee.model.dao.EmployeeEntity;
import com.sample.microservices.employee.model.dto.Employee;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
	
	EmployeeEntity EmployeeToEntity(Employee source);	
	List<EmployeeEntity> EmployeeToEntity(List<Employee> source);
	
	@Mapping(target="salary", expression="java(source.getSalary() != null ? source.getSalary() : 100000.00)")
	Employee entityToEmployee(EmployeeEntity source);
	List<Employee> entityToEmployee(List<EmployeeEntity> source);

}
