package com.sample.microservices.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.pagination.PageLayout;
import com.sample.microservices.employee.enums.ManagerSortType;
import com.sample.microservices.employee.model.dto.ManagerDto;
import com.sample.microservices.employee.model.dto.ManagerMini;
import com.sample.microservices.employee.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager-controller")
public class ManagerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
	
	private final ManagerService managerService;
	
	ManagerController(ManagerService managerService) {
		this.managerService = managerService;		
	}

	@Operation(summary="get salary by manager name using stored procedure")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="text/plain", schema=@Schema(implementation=String.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/id/{id}")
	public String getManagerMaxSalaryByName(@PathVariable("id") Long id) {
		return this.managerService.getSalaryById(id);
	}
	
	
	@Operation(summary="insert manager_bk using zeroInParamPr stored procedure")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="text/plain", schema=@Schema(implementation=String.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/zeroInParamPr")
	public void zeroInParamPr() {
		this.managerService.zeroInParamPr();
	}
	
	
	@Operation(summary="insert manager_bk using twoInParamPr stored procedure")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="text/plain", schema=@Schema(implementation=String.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/twoInParamPr/{id}/{inc}")
	public void twoInParamPr(@PathVariable("id") Long id, @PathVariable("inc") Double inc) {
		this.managerService.twoInParamPr(id, inc);
	}
	
	
	@Operation(summary="insert manager_bk using insertManagerBk stored procedure")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="text/plain", schema=@Schema(implementation=String.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/manager_bk/{id}")
	public void insertManagerBk(@PathVariable("id") Long id) {
		this.managerService.insertManagerBk(id);
	}
	
	
	@Operation(summary="get salary by manager name using stored procedure")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="text/plain", schema=@Schema(implementation=String.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/name/{name}")
	public String getManagerMaxSalaryByName(@PathVariable("name") String name) {
		return this.managerService.getManagerMaxSalary(name);
	}
	
	
	@Operation(summary="get an manager by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Manager.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Manager getManagerById(@PathVariable("id") Long id) {
		return this.managerService.getManagerById(id);
	}
	
	@Operation(summary="get all the managers")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Manager> getAllManagers() {
		return this.managerService.getAllManagers();
	}

	@Operation(summary="get all the managers with pagination and provided search filter-in")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/managers-containing")
	public PageLayout<Manager> getAllManagersWithPaginationAndContaining(
			@Parameter(name="name", description="Containing: name")
			@RequestParam(required=false) String name,
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value="sort", defaultValue = "ID") List<ManagerSortType> sort,
			@RequestParam(value="direction", defaultValue = "ASC") Sort.Direction direction
			) {
		return this.managerService.getAllManagersWithPaginationAndContaining(name, pageNum, pageSize, sort, direction);
	}

	@Operation(summary="get all the managers with pagination and provided search filter-in")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/name-in")
	public PageLayout<Manager> getAllManagersWithPaginationAndFilter(
			@Parameter(name="names", description="In: name(s) such as n1,n2, ... ...")
			@RequestParam(required=false) List<String> names,
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value="sort", defaultValue = "ID") List<ManagerSortType> sort,
			@RequestParam(value="direction", defaultValue = "ASC") Sort.Direction direction
			) {
		return this.managerService.getAllManagersWithPaginationAndFilter(names, pageNum, pageSize, sort, direction);
	}

	@Operation(summary="Create an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Manager created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Manager.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Manager with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Manager createManager(@Valid @RequestBody ManagerDto managerDto) {

		return this.managerService.createManager(managerDto);
	}
	
	@Operation(summary="Create a list of managers")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Managers created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Manager with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Manager> createManagers(@Valid @RequestBody List<ManagerDto> managerDtos) {

		return this.managerService.createManagers(managerDtos);
	}
	
	@Operation(summary="Update an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateManager(@PathVariable("id") Long id, @Valid @RequestBody ManagerDto managerDto) throws Exception {
		this.managerService.updateManager(id, managerDto);
	}
	
	@Operation(summary="Delete an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Manager deleted"), 
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteManager(@PathVariable("id") Long id) {

		this.managerService.deleteManagerById(id);
	}
	
	@Operation(summary="Delete all managers")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Managers deleted"), 
			@ApiResponse(responseCode="404",description="Some Manager does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllManagers() {

		this.managerService.deleteAllManagers();
	}
	
	@Operation(summary="Delete all managers by name")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Managers deleted"), 
			@ApiResponse(responseCode="404",description="Some Manager does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/delete/name/{name}")
	public void deleteManagersByName(@PathVariable("name") String name) {

		this.managerService.deleteManagersByName(name);
	}
	
	@Operation(summary="Delete all managers by nameLike")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Managers deleted"), 
			@ApiResponse(responseCode="404",description="Some Manager does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/delete/namelike/{name}")
	public void deleteManagersByNameLike(@PathVariable("name") String name) {

		this.managerService.deleteManagersByNameLike(name);
	}
	
	@GetMapping("/getManagersByName1/{name}")
	public List<Manager> getManagersByName1(@PathVariable("name") String name) {
		return this.managerService.getManagersByName1(name);
	}

	@GetMapping("/getManagersByName2/{name}")
	public List<Manager> getManagersByName2(@PathVariable("name") String name) {
		return this.managerService.getManagersByName2(name);
	}

	@GetMapping("/getManagersByName3/{name}")
	public List<Manager> getManagersByName3(@PathVariable("name") String name) {
		return this.managerService.getManagersByName3(name);
	}

	@GetMapping("/getManagersByNameIn1/{names}")
	public List<Manager> getManagersByNameIn1(@PathVariable("names") List<String> names) {
		return this.managerService.getManagersByNameIn1(names);
	}

	@GetMapping("/getManagersByNameIn2/{names}")
	public List<Manager> getManagersByNameIn2(@PathVariable("names") List<String> names) {
		return this.managerService.getManagersByNameIn2(names);
	}

	@GetMapping("/getManagersByNameLike/{name}")
	public List<Manager> getManagersByNameLike(@PathVariable("name") String name) {
		return this.managerService.getManagersByNameLike(name);
	}

	@GetMapping("/getManagersByNameLikeNative/{name}")
	public List<Manager> getManagersByNameLikeNative(@PathVariable("name") String name) {
		return this.managerService.getManagersByNameLikeNative(name);
	}

	@GetMapping("/getManagerMiniByNameLikeNative/{name}")
	public List<ManagerMini> getManagerMiniByNameLikeNative(@PathVariable("name") String name) {
		return this.managerService.getManagerMiniByNameLikeNative(name);
	}

	
	
}
