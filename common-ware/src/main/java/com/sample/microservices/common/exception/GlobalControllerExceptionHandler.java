package com.sample.microservices.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.sample.microservices.common.model.HttpErrorInfo;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalControllerExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
  
  @ExceptionHandler(NotFoundException.class)
  public HttpErrorInfo globalResourceNotFoundException(WebRequest request, NotFoundException ex) {
    
    return new HttpErrorInfo(HttpStatus.NOT_FOUND, request.getDescription(false), ex.getMessage());
    
  }  

  @ExceptionHandler(ValidationFailureException.class)
  public HttpErrorInfo globalglobalalidationFailureException(WebRequest request, ValidationFailureException ex) {
    
    return new HttpErrorInfo(HttpStatus.BAD_REQUEST, request.getDescription(false), ex.getMessage());
    
  }    
  
  @ExceptionHandler(InternalRuntimeException.class)
  public HttpErrorInfo globalInternalRuntimeException(WebRequest request, InternalRuntimeException ex) {
    
    return new HttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), ex.getMessage());
    
  }  
  
}
