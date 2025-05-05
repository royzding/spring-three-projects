package com.sample.microservices.hapifhir.service;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	private FhirContext fhirContext;

	public String createPatient(String patientJson) {
		Patient patient = fhirContext.newJsonParser().parseResource(Patient.class, patientJson);
		// Process and store the patient resource
		System.out.println("Received patient: " + patient.getName().get(0).getNameAsSingleString());
		return "Patient created successfully";
	}
}