package com.sample.microservices.multipledb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.multipledb.model.second.OrderEntity;
import com.sample.microservices.multipledb.model.second.UserEntity;
import com.sample.microservices.multipledb.service.MultipledbService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class MultipledbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultipledbController.class);
	
	private final MultipledbService multipledbService;
	
	MultipledbController(MultipledbService multipledbService) {
		this.multipledbService = multipledbService;		
	}

	@GetMapping("/employee/all")
	public List<Employee> getAllEmployees() {
		return this.multipledbService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return this.multipledbService.getEmployeeById(id);
	}
	
	@GetMapping("/manager/all")
	public List<Manager> getAllManagers() {
		return this.multipledbService.getAllManagers();
	}
	
	@GetMapping("/manager/{id}")
	public Manager getManagerById(@PathVariable("id") Long id) {
		return this.multipledbService.getManagerById(id);
	}
	
	@Operation(summary="Create an User")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="User created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=UserEntity.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Employee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public UserEntity createUser(@Valid @RequestBody UserEntity user) {

		return this.multipledbService.createUser(user);
	}	

	@GetMapping("/user/all")
	public List<UserEntity> getAllUsers() {
		return this.multipledbService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public UserEntity getUserById(@PathVariable("id") Long id) {
		return this.multipledbService.getUserById(id);
	}	
	
	@PostMapping("/order")
	@ResponseStatus(HttpStatus.CREATED)
	public OrderEntity createOrder(@Valid @RequestBody OrderEntity order) {

		return this.multipledbService.createOrder(order);
	}	

	@GetMapping("/order/all")
	public List<OrderEntity> getAllOrders() {
		return this.multipledbService.getAllOrders();
	}
	
	@GetMapping("/order/{id}")
	public OrderEntity getOrderById(@PathVariable("id") Long id) {
		return this.multipledbService.getOrderById(id);
	}	
}
