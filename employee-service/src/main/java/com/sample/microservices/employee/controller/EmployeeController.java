package com.sample.microservices.employee.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Department;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.EmployeeInfo;
import com.sample.microservices.employee.model.dao.EmployeeEntity;
import com.sample.microservices.employee.model.dto.EmployeeDto;
import com.sample.microservices.employee.model.dto.ValidEmployee;
import com.sample.microservices.employee.service.EmployeeService;
import com.sample.microservices.employee.service.PdfService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee-controller")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService employeeService;
	
	private final PdfService pdfService;
	
	EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
		this.pdfService = new PdfService();		
	}

	@GetMapping("/department/all")
	public List<Department> getAllDepartments() {
		return this.employeeService.getAllDepartments();
	}
	
	@Operation(summary="get an employee by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Employee",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Employee.class)) }),
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return this.employeeService.getEmployeeById(id);
	}
	
	@Operation(summary="get an employee by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Employee",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Employee.class)) }),
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/entity/{id}")
	public EmployeeEntity getEmployeeEntityById(@PathVariable("id") Long id) {
		return this.employeeService.getEmployeeEntityById(id);
	}
	
	@Operation(summary="get all the employees")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return this.employeeService.getAllEmployees();
	}

	@Operation(summary="get employees by depId")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/employees/{depId}")
	public List<Employee> getEmployeesByDepartmentId(@PathVariable("depId") Long depId) {
		return this.employeeService.getEmployeesByDepartmentId(depId);
	}

	@Operation(summary="Create an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Employee created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Employee.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Employee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

		return this.employeeService.createEmployee(employeeDto);
	}
	
	@Operation(summary="Create a list of employees")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Employees created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Employee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Employee> createEmployees(@Valid @RequestBody List<EmployeeDto> employeeDtos) {

		return this.employeeService.createEmployees(employeeDtos);
	}
	
	@Operation(summary="Update an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDto employeeDto) throws Exception {
		this.employeeService.updateEmployee(id, employeeDto);
	}
	
	@Operation(summary="Delete an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Employee deleted"), 
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {

		this.employeeService.deleteEmployeeById(id);
	}
	
	@Operation(summary="Delete all employees")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Employees deleted"), 
			@ApiResponse(responseCode="404",description="Some Employee does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllEmployees() {

		this.employeeService.deleteAllEmployees();
	}
	
	@Operation(summary="Create a ValidEmployee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="ValidEmployee created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=ValidEmployee.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="ValidEmployee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/validEmployee")
	@ResponseStatus(HttpStatus.CREATED)
	public ValidEmployee createValidEmployee(@Valid @RequestBody ValidEmployee validEmployee) {

		return validEmployee;
	}
	
	@GetMapping("/getEmployeesByManagerId/{mId}")
	public List<EmployeeInfo> getEmployeesByManagerId(@PathVariable("mId") Long mId) {
		return this.employeeService.getEmployeesByManagerId(mId);
	}

	@GetMapping("/getEmployeesByManagerIdAndDeptId/{mId}/{dId}")
	public List<EmployeeInfo> getEmployeesByManagerIdAndDeptId(@PathVariable("mId") Long mId, @PathVariable("dId") Long dId) {
		return this.employeeService.getEmployeesByManagerIdAndDeptId(mId, dId);
	}

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws IOException {
        byte[] pdfBytes = pdfService.generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "generated.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    
    @GetMapping("/create-pdf")
    public ResponseEntity<byte[]> createPdf() {
    	
        byte[] pdfBytes = pdfService.createPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "generated.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    
}
