package com.sample.microservices.department.service;

import org.springframework.stereotype.Service;

import com.sample.microservices.common.annotation.LoggableType;
import com.sample.microservices.common.exception.InternalRuntimeException;
import com.sample.microservices.common.exception.NotFoundException;
import com.sample.microservices.common.exception.ValidationFailureException;

@Service
@LoggableType
public class ExceptionTestService {
	
	public void testNotFoundException(final Long id) {
		
		throw new NotFoundException("Not found for this id: " + id);
	}

	public void testValidationFailureException() {
		
		throw new ValidationFailureException("validation failure message.");
	}	
	
	public void testInternalRuntimeException() {
		
		throw new InternalRuntimeException("internal runtime exception message.");
	}		
}
