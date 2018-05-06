package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	/*  POINTCUT EXPRESSIONS - takes parameter as Method(s)
	 *
	 * 	execution(public String getName()
	 * 	execution(public String model.Circle.getName()
	 * 	execution(public * get*())"
	 * 	execution(* get*(*))" 					// Any type of argument
	 * 	execution(* get*(..))" 					// Any no. and any type of argument
	 * 	execution(* model.*.get*(..))"
	 */
	
	@Before("execution(public * getN*())")
	public void LoggingAdvice() {
		System.out.println("\nLoggingAdvice() : getN*() method called");
	}
	
	
	/*  WITHIN EXPRESSIONS - takes parameter as Class(s)
	 *
	 * 	within(model.Circle)
	 * 	within(model.*)
	 */
	
	@Pointcut("execution(public * getN*())")
	public void allGetters() {}					// Dummy Method that holds pointcut expression
	
	@Pointcut("within(model.Circle)")
	public void allCircles() {}					// Dummy Method that holds pointcut expression
	
	@Before("allGetters()")
	public void LoggingAdvice_Pointcut() {
		System.out.println("\nLoggingAdvice_Pointcut() : Get method called");
	}
	
	
	/* Combining Pointcuts - allGetters() && allCircles()
	 * 
	 * JoinPoint(AOP terminology) has actual information about the actual method call that triggered the advice.
	 * alternatively, all the places in your code, where you can apply advice.
	 * or, simply it contains information about the method.
	 * 
	 * joinPoint.toString()  - gives information about called method
	 * joinPoint.getTarget() - gives Object, whose method is called
	 */
	
	@Before("allGetters() && allCircles()")
	public void LoggingAdvice_Combining_Pointcuts(JoinPoint joinPoint) {
		System.out.println("\nLoggingAdvice_Combining_Pointcuts() : method info : "+joinPoint.toString());
		System.out.println("LoggingAdvice_Combining_Pointcuts() : Object info : "+joinPoint.getTarget());
	}
	
	
	@Before("args(String)")
	public void LoggingAdvice_ArgumentMethods() {
		System.err.println("\nA method which takes String argument has been called");
	}
	
	@Before("args(name)")
	public void LoggingAdvice_ArgumentMethods(String name) {
		System.err.println("\nA method which takes String argument with value='"+name+"' has been called");
	}
}
