package com.sample.microservices.multipledb.model.first;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Getter
@Setter
public class EmployeeEntity  extends ParentEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="salary")
	private Double salary;

	
}
