package com.sample.microservices.multipledb.model;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="manager", schema = "c##ding")

@Getter
@Setter
public class ManagerEntity extends ParentEntity {
	
	private static final long serialVersionUID = 3L;
	
	@Column(name="salary")
	private Double salary;
	
}
