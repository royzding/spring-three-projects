package com.sample.microservices.batch.data.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")

@Getter
@Setter
public class Person  implements Serializable {
	
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="country")
	private String country;

	@Column(name="modified_by")
	private String modifiedBy = "Base-Dummy";

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", modifiedBy=" + modifiedBy + "]";
	}
	
}