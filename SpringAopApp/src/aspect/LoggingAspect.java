package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	/*  POINTCUT EXPRESSIONS - takes parameter as Method(s)
	 
	 * 	execution(public String getName()
	 * 	execution(public String model.Circle.getName()
	 * 	execution(public * get*())"
	 * 	execution(* get*(*))" 					// Any type of argument
	 * 	execution(* get*(..))" 					// Any no. and any type of argument
	 * 	execution(* model.*.get*(..))"
	 */
	
	public static final String pointcutExpression 
	= "execution(* model.Circle.*(..))";
	
	@Before("execution(public * getN*())")
	public void LoggingAdvice() {
		System.out.println("LoggingAdvice() : Get method called");
	}
	
	
	
	/*  WITHIN EXPRESSIONS - takes parameter as Class(s)
	 
	 * 	within(model.Circle)
	 * 	within(model.*)
	 */
	
	@Pointcut(LoggingAspect.pointcutExpression)
	public void allGetters() {}					// Dummy Method that holds pointcut expression
	
	public static final String pointcutExpression_within 
	= "within(model.Circle)";
	
	@Pointcut(LoggingAspect.pointcutExpression_within)
	public void allCircles() {}					// Dummy Method that holds pointcut expression
	
	@Before("allGetters()")
	public void LoggingAdvice_Pointcut() {
		System.out.println("LoggingAdvice_Pointcut() : Get method called");
	}
	
	@Before("allGetters() && allCircles()")		// Combining Pointcuts
	public void LoggingAdvice_Combining_Pointcuts() {
		System.out.println("LoggingAdvice_Combining_Pointcuts() : method called");
	}
	
}
