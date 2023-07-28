package com.sample.microservices.department.data.model;

import java.io.Serializable;

import com.sample.microservices.common.listener.EntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="department_holiday_date_mapping")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(EntityListener.class)
public class DepartmentHolidayMappingDateEntity  implements Serializable {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="department_id")
	private Long departmentId;

	@NotNull
	@Column(name="holiday_date_id")
	private Long holidayDateId;

}
