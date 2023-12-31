package com.sample.microservices.organization.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.organization.model.EmployeeEntity;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
	
	EmployeeEntity employeeToEntity(Employee source);
	
	List<EmployeeEntity> employeeToEntity(List<Employee> source);
	
	Employee entityToEmployee(EmployeeEntity source);

	List<Employee> entityToEmployee(List<EmployeeEntity> source);
}
