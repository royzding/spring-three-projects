package com.sample.microservices.common.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class HttpErrorInfo {
	
  private ZonedDateTime timestamp;
  private String path;
  private int status;
  private String message;
  private String error;

  public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
	this.timestamp = ZonedDateTime.now();
    this.status = httpStatus.value();
    this.path = path;
    this.message = message;
    this.error = httpStatus.getReasonPhrase();
  }

}
