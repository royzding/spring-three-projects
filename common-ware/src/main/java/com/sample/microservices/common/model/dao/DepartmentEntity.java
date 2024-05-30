package com.sample.microservices.common.model.dao;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Department")

@Getter
@Setter
public class DepartmentEntity extends ParentEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
}
