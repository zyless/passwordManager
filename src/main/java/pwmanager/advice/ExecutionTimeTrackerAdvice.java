package pwmanager.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class ExecutionTimeTrackerAdvice {

	@Around("@annotation(pwmanager.advice.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable{
		long startTime=System.currentTimeMillis();
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		log.info("Method name " +pjp.getSignature() + " time taken to execute " + (endTime - startTime));
		return obj;
	}
	
	
}
