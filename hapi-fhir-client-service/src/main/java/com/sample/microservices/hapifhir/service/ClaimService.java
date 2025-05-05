package com.sample.microservices.hapifhir.service;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.r4.model.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {

	@Autowired
	private FhirContext fhirContext;

	public String createClaim(String claimJson) {
		Claim claim = fhirContext.newJsonParser().parseResource(Claim.class, claimJson);
		// Process and store the patient resource
		System.out.println("Received claim: " + claim.getPatient().getReference());
		return "Claim created successfully";
	}
}