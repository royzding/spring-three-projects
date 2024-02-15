package com.sample.microservices.department.data.model;

import org.hibernate.annotations.DynamicUpdate;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Table(name="DEPARTMENT", schema = "\"C##WANZUN\"")

@Getter
@Setter
public class DepartmentWZEntity extends ParentEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
}
