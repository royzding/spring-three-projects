package com.sample.microservices.employee.model.dao;

import com.sample.microservices.common.model.dao.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="manager")

@Getter
@Setter
public class ManagerEntity extends BaseEntity {
	
	private static final long serialVersionUID = 3L;
	
	@Column(name="salary")
	private Double salary;
	
}
