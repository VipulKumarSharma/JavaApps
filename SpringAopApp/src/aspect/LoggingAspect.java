package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	
	/*	These advices will only be executed for the call we make (not by the Spring container) */
	
	
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
	
	@Pointcut("execution(public * get*())")
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
		System.err.println("\n[@Before] A method which takes String argument with value='"+name+"' has been called");
	}
	
	
	/* @After runs after our target method runs (no matter whether it completes or NOT) 
	 * 
	 * @AfterReturning("args(name)")
	 * To access returning value from the target() :: 
	 * 
	 * @AfterReturning(pointcut="args(name)", returning="returnString")
	 * public void <funcName>(String name, String returnString) {...}
	 * 
	 * 
	 * @AfterThrowing("args(name)")
	 * similarly for @AfterThrowing ::
	 * 
	 * @AfterReturning(pointcut="args(name)", throwing="ex")
	 * public void <funcName>(String name, Exception ex) {...}
	 */
	
	@After("args(name)")
	public void LoogingAdvice_After(String name) {
		System.err.println("\n[@After] A method which takes String argument with value='"+name+"' has been called");
	}
	
	@AfterReturning(pointcut="args(name)", returning="returnString")
	public void LoogingAdvice_AfterReturning(String name, String returnString) {
		System.err.println("\n[@AfterReturning] A method which takes String argument has been returned, "
				+ "\nhaving argument='"+name+"' and returned value='"+returnString+"'");
	}
	
	@AfterThrowing(pointcut="args(name)", throwing="ex")
	public void LoogingAdvice_AfterThrowing(String name, Exception ex) {
		System.out.println("\n[@AfterThrowing] An eception has been thrown from a method, "
				+ "\nwhich is : "+ex);
	}
	
	
	/* @Around("allGetters()")
	 * @Around("@annotation(aspect.Loggable)") - allows us to apply the corresponding advice 
	 * & @Loggable to any method just by annotating the target method. 
	 * By this approach you don't have to worry about name & naming conventions.
	 * You can apply @Loggable to any method, without changing pointcut expressions.
	 * 
	 * Note :- "proceedingJoinPoint.proceed()" proceed the target() execution
	 * 
	 * Advantages of @Around advice :
	 * 1. 	More control of the code.
	 * 2. 	You can have shared variables inside @Around advice, 
	 * 	  	before & after target() execution, in a thread safe method.
	 * 3. 	You can get hold of returned value.
	 * 
	 * Note :- If target() returns some value then Around advice also have to do the same
	 */
	
	@Around("@annotation(aspect.Loggable)")
	public Object LoggingAdvice_Around(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnedValue = null;
		try {
			System.err.println("\n[@Around] Before advice");
			returnedValue = proceedingJoinPoint.proceed();
			System.err.println("[@Around] After advice");
			
		} catch (Throwable e) {
			System.err.println("[@Around] After Throwing");
		}
		
		System.err.println("[@Around] After finally\n");
		
		return returnedValue;
	}
	
}