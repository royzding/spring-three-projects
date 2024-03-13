package com.sample.microservices.department.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.department.data.model.Student;
import com.sample.microservices.department.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Student Service", description = "REST API for Student Service.")
@RestController
@RequestMapping("/Student-controller")
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	private final StudentService StudentService;
	
	StudentController(StudentService StudentService) {
		this.StudentService = StudentService;		
	}
	
	@Operation(summary="get an Student by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Student",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Student.class)) }),
			@ApiResponse(responseCode="404",description="Student does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") Long id) {
		return this.StudentService.getStudentById(id);
	}
	
	@Operation(summary="get all the Students")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Student.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Student> getAllStudents() {
		return this.StudentService.getAllStudents();
	}

	@Operation(summary="Create an Student")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Student created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Student.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Student with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@Valid @RequestBody Student student) {

		return this.StudentService.createStudent(student);
	}
	
	@Operation(summary="Create a list of Students")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Students created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Student.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Student with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Student> createStudents(@Valid @RequestBody List<Student> students) {

		return this.StudentService.createStudents(students);
	}
	
	@Operation(summary="Update an Student")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Student does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStudent(@PathVariable("id") Long id, @Valid @RequestBody Student student) throws Exception {
		this.StudentService.updateStudent(id, student);
	}
	
	@Operation(summary="Delete an Student")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Student deleted"), 
			@ApiResponse(responseCode="404",description="Student does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable("id") Long id) {

		this.StudentService.deleteStudentById(id);
	}
	
}
