package com.sample.microservices.common.model.dao;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="holiday_date")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HolidayDateEntity extends ParentEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	@NotNull
	@Column(name="day")
	private Integer day;

	@NotNull
	@Column(name="month")
	private Integer month;

	@NotNull
	@Column(name="year")
	private Integer year;
	
}
