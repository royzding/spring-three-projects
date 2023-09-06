package com.sample.microservices.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.enums.ActivityType;
import com.sample.microservices.common.service.AuditLoggingService;

@Aspect
@Component
public class AuditLoggingAspect {
	
	private final AuditLoggingService auditLoggingService;
	
	public AuditLoggingAspect(AuditLoggingService auditLoggingService) {		
		this.auditLoggingService = auditLoggingService;		
	}
	
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {}

    @Pointcut("execution(public * *(..)) && within(com.sample.microservices.*.controller.*))")
    public void controllerMapping() {}
    
    @Around("getMapping() && controllerMapping()")
    public Object logGetMapping(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
    	return this.auditLoggingService.auditActivity(proceedingJoinPoint, ActivityType.GET);    	
    }
    
    @Around("postMapping() && controllerMapping()")
    public Object logPostMapping(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
    	return this.auditLoggingService.auditActivity(proceedingJoinPoint, ActivityType.POST);    	
    }
    
    @Around("deleteMapping() && controllerMapping()")
    public Object logDeleteMapping(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
    	return this.auditLoggingService.auditActivity(proceedingJoinPoint, ActivityType.DELETE);    	
    }
    
}
