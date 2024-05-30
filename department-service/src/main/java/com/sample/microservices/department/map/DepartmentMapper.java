package com.sample.microservices.department.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.Department;
import com.sample.microservices.common.model.dao.DepartmentEntity;
import com.sample.microservices.department.data.model.DepartmentWZEntity;
import com.sample.microservices.department.model.dto.DepartmentDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
	
	DepartmentEntity departmentDtoToEntity(DepartmentDto source);
	
	List<DepartmentEntity> departmentDtoToEntity(List<DepartmentDto> source);
	
	DepartmentWZEntity departmentToEntity(Department source);

	List<DepartmentWZEntity> departmentToEntity(List<Department> source);
	
	Department entityToDepartment(DepartmentEntity source);

	List<Department> entityToDepartment(List<DepartmentEntity> source);

	Department entityWZToDepartment(DepartmentWZEntity source);

	List<Department> entityWZToDepartment(List<DepartmentWZEntity> source);

	@Mappings({
		@Mapping(target="id", source="id")		
	})
	Department departmentDtoToDepartment(DepartmentDto source);

}
