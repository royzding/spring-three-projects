package com.sample.microservices.common.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.enums.ActivityType;
import com.sample.microservices.common.enums.LogLevel;

@Service
public class AuditLoggingServiceImpl implements AuditLoggingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditLoggingServiceImpl.class);

	@Override
	public Object auditActivity(ProceedingJoinPoint point, ActivityType type) throws Throwable {
		
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        String targetClassName = point.getTarget().getClass().getSimpleName();
        String targetMethodName = point.getSignature().getName();
        Object[] args = point.getArgs();       

        StopWatch timer = new StopWatch();
		
		timer.start();
		
		try {
				Object value = point.proceed();
				
				timer.stop();
				
				this.loggings(type, LogLevel.SUCCESS, className, methodName, timer, "Successful!");
				
				return value;
			
		} catch (Throwable t) {
			
			timer.stop();	
			
			this.loggings(type, LogLevel.ERROR, className, methodName, timer, t.getMessage());
			
			throw t;
			
		}

	}
	
	private void loggings(ActivityType type, LogLevel logLevel, String className, String methodName, StopWatch timer, String msg) {
		
		String loadString = "AuditActivity:{" + className +";"+ methodName + ";" + type + ";time["+ timer.getTotalTimeMillis()+"millis];" + "msg\"" + msg + "\"}";
		
		switch (logLevel) {
		
			case FAIL: 
			case ERROR:
				LOGGER.error("{}", loadString);
				break;
			default:
				LOGGER.info("{}", loadString);
		}
		
	}

}
