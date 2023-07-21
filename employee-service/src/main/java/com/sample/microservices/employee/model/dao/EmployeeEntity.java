package com.sample.microservices.employee.model.dao;

import com.sample.microservices.common.model.dao.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Data
@Getter
@Setter
public class EmployeeEntity extends BaseEntity {
	
	  private static final long serialVersionUID = 2L;
	
   	  @Column(name = "dep_id")
	  private Integer depId;
	    
	  @Column(name = "manager_id")
	  private Integer managerId;
	    
	  @Column(name = "salary")
	  private Double salary;
	
}

//https://medium.com/the-soft-tradeoff/jpa-attribute-converters-are-great-dont-use-attribute-converters-8e08ea5fa797
//JPA attribute converters
