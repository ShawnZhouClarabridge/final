package co.edureka.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Before("execution(* co.edureka.controller.CourseController.*(..))")
	public void logFirst(JoinPoint joinPoint) {
		System.out.println("******");
		System.out.println("logFirst() is running!");
		System.out.println("Intercepted : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
}
