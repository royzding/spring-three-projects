package com.sample.microservices.common.controller;

import com.sample.microservices.common.versions.v21.SwitchPatternMatching;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "Common Ware", description = "REST API from Common Ware.")
@RestController
@RequestMapping("/switch-pattern-matching")
public class SwitchPatternMatchingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwitchPatternMatchingController.class);

	private final SwitchPatternMatching switchPatternMatching;

    public SwitchPatternMatchingController(SwitchPatternMatching switchPatternMatching) {
        this.switchPatternMatching = switchPatternMatching;
    }


    @Operation(summary="get a file content")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success"),
			@ApiResponse(responseCode="404",description="file content does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	
	@GetMapping
	public void getResults() throws IOException {
		switchPatternMatching.getResults();
	}
}


