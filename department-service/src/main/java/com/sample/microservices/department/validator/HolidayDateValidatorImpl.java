package com.sample.microservices.department.validator;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sample.microservices.common.model.dao.HolidayDateEntity;
import com.sample.microservices.common.model.dto.HolidayDateDto;
import com.sample.microservices.common.repository.HolidayDateRepository;

@Service
public class HolidayDateValidatorImpl implements HolidayDateValidator{
	
	private final HolidayDateRepository repository;
	
	HolidayDateValidatorImpl(HolidayDateRepository repository) {
		this.repository = repository;		
	}
	
	@Override
	public void validateHolidayDateUnique(HolidayDateDto holidayDateDto) throws Exception {
		
		Optional<HolidayDateEntity> nwEntity = 
				this.repository.findByNameAndDayAndMonthAndYear(
						holidayDateDto.getName(), holidayDateDto.getDay(), holidayDateDto.getMonth(), holidayDateDto.getYear());
		
		if(nwEntity.isPresent()) {
			throw new Exception("Holiday Date Existed");
		}
		
	}
	
	

}
