package com.sample.microservices.multipledb.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.multipledb.model.ManagerEntity;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface ManagerMapper {
	
	@Mapping(target="name", source="name")		
	ManagerEntity managerToEntity(Manager source);
	
	List<ManagerEntity> managerToEntity(List<Manager> source);
	
	Manager entityToManager(ManagerEntity source);

	List<Manager> entityToManager(List<ManagerEntity> source);

}
