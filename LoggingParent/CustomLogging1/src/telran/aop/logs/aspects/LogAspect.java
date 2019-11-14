package telran.aop.logs.aspects;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import telran.aop.document.ResDoc;
import telran.aop.service.ILoggingService;

@Aspect
public class LogAspect {
	@Autowired
	ILoggingService serviceLogging;
	
	
	//@Pointcut(value="execution(* telran.aop.logs.controller.LogsTestController.*(..))")
	
	@Pointcut(value="@within(org.springframework.web.bind.annotation.RestController)")
	private void pointCatController(){
		
	}
	
	String[] methodNames;
	
	public LogAspect(String ...names) {
		methodNames=names;
	}
	
	
	@Around(value="pointCatController()")
	public Object logAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Instant start=Instant.now();
		LocalDateTime dateTime=LocalDateTime.ofInstant(start, ZoneOffset.UTC);
		Object res=joinPoint.proceed();
	
		String className=joinPoint.getTarget().getClass().getName();
		String methodName=joinPoint.getSignature().getName();
		if(methodNames==null || methodNames.length==0 || Arrays.stream(methodNames).anyMatch(name->methodName.toLowerCase().contains(name.toLowerCase()))) {
			LocalDateTime current=LocalDateTime.now();
			System.out.printf("date-time: %s; class: %s; method:%s"+ " response time: %d\n",current.toString(), className, methodName, 
					ChronoUnit.MILLIS.between(start, Instant.now()));
		serviceLogging.addLog(new ResDoc(dateTime, className, methodName, ChronoUnit.MILLIS.between(start, Instant.now()), res.toString(), null));
			
		}
				return res;
	}
		@AfterThrowing(value="pointCatController()", throwing="ex")
		public void logExceptions( JoinPoint joinPoint, Exception ex) {
			System.out.printf("method: %s thrown exception: %s:",joinPoint.getSignature().getName(), ex.getMessage());
			Instant start=Instant.now();
			LocalDateTime dateTime=LocalDateTime.ofInstant(start, ZoneOffset.UTC);
			
			
			String className=joinPoint.getTarget().getClass().getName();
			String methodName=joinPoint.getSignature().getName();
			serviceLogging.addLog(new ResDoc(dateTime, className, methodName, ChronoUnit.MILLIS.between(start, Instant.now()), null, ex.getMessage()));
		}
		
		@PostConstruct
		public void getAspect() {
			System.out.println("Aspect in application context");
		}
	
	
	
}
