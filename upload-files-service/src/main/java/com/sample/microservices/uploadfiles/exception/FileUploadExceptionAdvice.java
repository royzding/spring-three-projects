package com.sample.microservices.uploadfiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sample.microservices.uploadfiles.message.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseMessage> handleMaxSizeException(Exception exc) {
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseMessage("One or more files are too large!"));
  }
}