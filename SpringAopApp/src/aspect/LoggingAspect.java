package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	public static final String pointcutExpression = "execution(public * getN*())";
	
	/* execution(public String getName()
	 * execution(public String model.Circle.getName()
	 * execution(public * get*())"
	 * execution(* get*(*))" 					// Any type of argument
	 * execution(* get*(..))" 					// Any no. and any type of argument
	 * execution(* model.*.get*(..))"
	 */
	
	
	@Before("execution(public * getN*())")
	public void LoggingAdvice() {
		System.out.println("LoggingAdvice() : Get method called");
	}
	
	@Before("allGetters()")
	public void LoggingAdvice_Pointcut() {
		System.out.println("LoggingAdvice_Pointcut() : Get method called");
	}
	
	@Pointcut(LoggingAspect.pointcutExpression)
	public void allGetters() {}				// Dummy Method that holds pointcut expression
}
