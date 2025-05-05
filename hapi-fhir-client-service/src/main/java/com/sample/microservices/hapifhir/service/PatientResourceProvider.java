package com.sample.microservices.hapifhir.service;

import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientResourceProvider implements IResourceProvider {

	@Override
	public Class<Patient> getResourceType() {
		return Patient.class;
	}

	@Read()
	public Patient readPatient(IdType id) {
		// Return a mock patient for demonstration
		Patient patient = new Patient();
		patient.setId(id.getValue());
		patient.addName().setFamily("Doe").addGiven("John");
		patient.setGender(org.hl7.fhir.r4.model.Enumerations.AdministrativeGender.MALE);
		return patient;
	}
}