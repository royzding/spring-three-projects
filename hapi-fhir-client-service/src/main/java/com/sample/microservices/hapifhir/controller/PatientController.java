package com.sample.microservices.hapifhir.controller;

import com.sample.microservices.hapifhir.service.ClaimService;
import com.sample.microservices.hapifhir.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private ClaimService claimService;

	@PostMapping("/Patient")
	public String createPatient(@RequestBody String patientJson) {
		return this.patientService.createPatient(patientJson);
	}

	@PostMapping("/Claim")
	public String createClaim(@RequestBody String claimJson) {
		return this.claimService.createClaim(claimJson);
	}
}