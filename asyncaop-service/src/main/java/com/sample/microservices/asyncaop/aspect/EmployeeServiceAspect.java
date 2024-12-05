package com.sample.microservices.asyncaop.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.sample.microservices.asyncaop.model.Employee;

@Aspect
@Component
public class EmployeeServiceAspect {
/*
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceAspect.class);
		
	@Around("execution(* com.sample.microservices.asyncaop.service.EmployeeService.getAllEmployees(..))")
    public List<Employee> aroundGetAllEmployeesAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
         
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();       
         
        final StopWatch stopWatch = new StopWatch();
        
        List<Employee> result = new ArrayList<>();
        
        try {
        	
            //Measure method execution time
            stopWatch.start();
            result = (List<Employee>)proceedingJoinPoint.proceed();
            stopWatch.stop();
            
        } catch(Exception ex) {
        	
        } finally {
            //Do Something useful, If you have
        }
 
        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
 
        return result;
    }	
	
    @Pointcut("execution(public * *(..)")
    public void allPublicMethodsExecution() {}
    
    @Pointcut("@annotation(org.springframework.transaction.annotation.GetMapping)")
    public void anyJoinPointWithGetMappingAnnotation() {}
    
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void anyJoinPointWithTransactionalAnnotation() {}
    
        
    @Before("allPublicMethodsExecution() && anyJoinPointWithTransactionalAnnotation()")
    public void logBeforeAllMethods(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
    }
 */   
	
/*
 
The execution of any public method:

    @Pointcut("execution(public * *(..)")
    public void allPublicMethodsExecution() {}
    
Any join point (method execution only in Spring AOP) where the executing method has an @Transactional annotation:

    @Pointcut("@annotation(org.springframework.transaction.annotation.GetMapping)")
    public void anyJoinPointWithGetMappingAnnotation() {}
    
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void anyJoinPointWithTransactionalAnnotation() {}
    
        
    @Before("allPublicMethodsExecution() && anyJoinPointWithTransactionalAnnotation()")
    public void logBeforeAllMethods(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
    }
    
    
The execution of any method with a name that begins with set:

    execution(* set*(..))
The execution of any method defined by the AccountService interface:

    execution(* com.xyz.service.AccountService.*(..))
The execution of any method defined in the service package:

    execution(* com.xyz.service.*.*(..))
The execution of any method defined in the service package or one of its sub-packages:

    execution(* com.xyz.service..*.*(..))
Any join point (method execution only in Spring AOP) within the service package:

    within(com.xyz.service.*)
Any join point (method execution only in Spring AOP) within the service package or one of its sub-packages:

    within(com.xyz.service..*) 	
    
	
 */

/*	
 ////////////////Before Advice methods/////////////////////////////
  
    @Before("execution(* com.sample.microservices.service.EmployeeService.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
    }
     
    @Before("execution(* com.sample.microservices.service.EmployeeService.getEmployeeById(..))")
    public void logBeforeGetEmployee(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logBeforeGetEmployee() : " + joinPoint.getSignature().getName());
    }
     
    @Before("execution(* com.sample.microservices.service.EmployeeService.createEmployee(..))")
    public void logBeforeCreateEmployee(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logBeforeCreateEmployee() : " + joinPoint.getSignature().getName());
    }
     
 ////////////////After Advice methods///////////////////////////
  
    @After("execution(* com.sample.microservices.service.EmployeeService.*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logAfterAllMethods() : " + joinPoint.getSignature().getName());
    }
     
    @After("execution(* com.sample.microservices.service.EmployeeService.getEmployeeById(..))")
    public void logAfterGetEmployee(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logAfterGetEmployee() : " + joinPoint.getSignature().getName());
    }
     
    @After("execution(* com.sample.microservices.service.EmployeeService.createEmployee(..))")
    public void logAfterCreateEmployee(JoinPoint joinPoint) 
    {
        System.out.println("****LoggingAspect.logAfterCreateEmployee() : " + joinPoint.getSignature().getName());
    }
     
 ////////////////AfterReturning Advice methods///////////////////////////
  
    @AfterReturning("execution(* com.sample.microservices.service.EmployeeService.*(..))")
    public void logAfterReturningAllMethods() throws Throwable 
    {
        System.out.println("****LoggingAspect.logAfterReturningAllMethods() ");
    }
 
    @AfterReturning(pointcut="execution(* com.sample.microservices.service.EmployeeService.getEmployeeById(..))", returning="retVal") 
    public void logAfterReturningGetEmployee(Object retVal) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAfterReturningGetEmployee() ");
        System.out.println(((EmployeeDTO)retVal).getId());
    }
 
    @AfterReturning("execution(* com.sample.microservices.service.EmployeeService.createEmployee(..))")
    public void logAfterReturningCreateEmployee() throws Throwable 
    {
        System.out.println("****LoggingAspect.logAfterReturningCreateEmployee() ");
    }
     
 ////////////////AfterThrowing Advice methods///////////////////////////
  
    @AfterThrowing (pointcut = "execution(* com.sample.microservices.service.EmployeeService.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }
     
 ////////////////Around Advice methods///////////////////////////
  
    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.sample.microservices.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
         
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();       
         
        final StopWatch stopWatch = new StopWatch();
        
        Object result = null;
        
        try {
        	
            //Measure method execution time
            stopWatch.start();
            result = proceedingJoinPoint.proceed();
            stopWatch.stop();
            
        } catch(Exception ex) {
        	
        } finally {
            //Do Something useful, If you have
        }
 
        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
 
        return result;
    }	
    

    @Around("execution(* com.sample.microservices.service.EmployeeService.*(..))")
    public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
 
    @Around("execution(* com.sample.microservices.service.EmployeeService.getEmployeeById(..))") 
    public void logAroundGetEmployee(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
 
    @Around("execution(* com.sample.microservices.service.EmployeeService.createEmployee(..))")
    public void logAroundCreateEmployee(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }    

    
class Test {

@override
	public String a(){
		b();
	}
}

private String b() {
	//c();
}   

class Test2 {
	public String b() {}   
}
    //intercept the called method A() which is inside of another calling method such as getEmployee().
    // called method A() needs to be in another class or interface and put @Around the method
     
    @Around("execution(* com.sample.microservices.service.EmployeeService.getEmployee(..))") 
    public void logAroundGetEmployee(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
   
    @Around("execution(* com.sample.microservices.service.calledMethods.A(..))") 
    public void logAroundA(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
   
    
*/
	
}
