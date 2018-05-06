package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
	
	// @Before("execution(public String getName())")
	// @Before("execution(public String model.Circle.getName())")
	// @Before("execution(public * get*())")
	// @Before("execution(* get*(*))") // Any type of argument
	// @Before("execution(* get*(..))") // Any no. and any type of argument
	@Before("execution(* model.*.get*(..))")
	public void LoggingAdvice() {
		System.out.println("Advice run, Get method called");
	}
}
