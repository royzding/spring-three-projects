package com.sample.microservices.employee.model.dao;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Getter
@Setter
public class EmployeeEntity extends ParentEntity {
	
	private static final long serialVersionUID = 2L;
	
	@Column(name="salary")
	private Double salary;
	
	@Column(name="dep_id")
	private Long depId;

/*	
	@Column(name="dep_type")
	@Enumerated(EnumType.STRING)
	private DepartmentType departmentType;
*/
	
	@OneToOne
	@JoinColumn(name="manager_id")
	private ManagerEntity managerEntity;
	
	
}
