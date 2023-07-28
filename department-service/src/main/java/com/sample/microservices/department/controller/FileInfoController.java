package com.sample.microservices.department.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Common Ware", description = "REST API from Common Ware.")
@RestController
@RequestMapping("/file-info")
public class FileInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoController.class);
	
	@Value("classpath:fileInfo.json")
	private Resource myResource;
	
	@Operation(summary="get an department by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success"),
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping
	public String getFileInfo() throws IOException {
		return new String(Files.readAllBytes(myResource.getFile().toPath()));
	}

	@GetMapping("/{date}")
	public LocalDate getDateInfo(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
		return date;
	}
	
}
