package com.sample.microservices.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.department.service.ExceptionTestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Common Ware", description = "REST API from Common Ware.")
@RestController
@RequestMapping("/exception-test")
public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
	
	@Autowired
	private ExceptionTestService exceptionTestService;
	
	@Operation(summary="get an department by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success"),
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/not-found/{id}")
	public void testNotFoundException(@PathVariable("id") Long id) {
		exceptionTestService.testNotFoundException(id);
	}

	@GetMapping("/validate")
	public void testValidationFailureException() {
		exceptionTestService.testValidationFailureException();
	}	
	
	@GetMapping("/internal-error")
	public void testInternalRuntimeException() {
		exceptionTestService.testInternalRuntimeException();
	}		
}
