package com.sample.microservices.common.enums;

public enum LogLevel {
	FAIL("Fail"),
	ERROR("Error"),
	SUCCESS("Success");
	
	private final String value;
	
	LogLevel(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
